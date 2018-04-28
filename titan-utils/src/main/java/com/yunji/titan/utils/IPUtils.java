package com.yunji.titan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by haoyuexun on 2018/4/27.
 */


public class IPUtils {
    private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

    public static Collection<InetAddress> getAllHostAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Collection<InetAddress> addresses = new ArrayList<InetAddress>();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    if(networkInterface.isVirtual()){
                        continue;
                    }
                    InetAddress inetAddress = inetAddresses.nextElement();
                    addresses.add(inetAddress);
                    logger.info("所有的ip是->{}", inetAddress.getHostAddress());
                }
            }
            return addresses;
        } catch (SocketException e) {
            logger.error("获取本地IP异常{}", e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public static Collection<String> getAllNoLoopbackAddresses() {
        Collection<String> noLoopbackAddresses = new ArrayList<String>();
        Collection<InetAddress> allInetAddresses = getAllHostAddress();

        for (InetAddress address : allInetAddresses) {
            if (!address.isLoopbackAddress()  && !address.isLinkLocalAddress() && address.isSiteLocalAddress()) {
                noLoopbackAddresses.add(address.getHostAddress());
                logger.info("没有环路的ip地址是：{}", address.getHostAddress());
            }
        }
        return noLoopbackAddresses;
    }

    public static String getFirstHostAddress() {
        Collection<String> allNoLoopbackAddresses = getAllNoLoopbackAddresses();
//        Iterator<String> iAddress = allNoLoopbackAddresses.iterator();
//        if(!allNoLoopbackAddresses.isEmpty() ) {
//            logger.info("最后返回的本地IP是：" + iAddress.next());
//            return iAddress.next();
//        }
        List addressList = allNoLoopbackAddresses.parallelStream().collect(Collectors.toList());
        if(addressList != null){
            // 取最后一个ip 为的是过滤掉第一个docker的ip,选第二个才是主机ip
            String ip = (String) addressList.get(addressList.size()-1);
            logger.info("最后返回的本地IP是：" + ip);
            return ip;
        }

        return "获取本地ip失败";
    }
}
