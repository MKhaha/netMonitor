package com.ywgroup.iecloud.util;

import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by guotao on 2017/7/25.
 * com.ywgroup.iecloud.util
 * netMonitor
 */
@Service("netMonitor")
public class NetMonitor {

    private Logger logger = LoggerFactory.getLogger(NetMonitor.class);

    private static int rxSpeed = 0;
    private static int txSpeed = 0;

    private static Sigar sigar = new Sigar();

    public static int getRxSpeed() {
        return rxSpeed;
    }

    public static int getTxSpeed() {
        return txSpeed;
    }

    public void getRxAndTxSpeed(){
        try {

            NetInterfaceConfig config = sigar.getNetInterfaceConfig(null);

            long start = System.currentTimeMillis();
            NetInterfaceStat statStart = sigar.getNetInterfaceStat(config.getName());
            Thread.sleep(2000);
            NetInterfaceStat statEnd = sigar.getNetInterfaceStat(config.getName());
            long end = System.currentTimeMillis();

            long rxBytesStart = statStart.getRxBytes();
            long txBytesStart = statStart.getTxBytes();

            long rxBytesEnd = statEnd.getRxBytes();
            long txBytesEnd = statEnd.getTxBytes();

            long rxSpeed = (rxBytesEnd - rxBytesStart) * 1000 / (end - start);
            long txSpeed = (txBytesEnd - txBytesStart) * 1000 / (end - start);

            logger.debug("config.getName() = [" + config.getName() + "]");
            logger.debug("config.getAddress() = [" + config.getAddress() + "]");
            logger.info("rxBytesEnd = [" + rxBytesEnd + " Bytes], rxBytesStart = [" + rxBytesStart + " Bytes]");
            logger.info("txBytesEnd = [" + txBytesEnd + " Bytes], txBytesStart = [" + txBytesStart + " Bytes]");
            logger.warn("rxSpeed = " + rxSpeed + " Bytes," + "txSpeed = " + txSpeed + " Bytes;");

        } catch (Exception e) {

            logger.error(e.getLocalizedMessage());

            // Restore the interrupted status
            Thread.currentThread().interrupt();
        }

    }
}
