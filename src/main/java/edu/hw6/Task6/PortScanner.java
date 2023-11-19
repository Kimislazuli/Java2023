package edu.hw6.Task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:EmptyBlock"})
public final class PortScanner {
    private PortScanner() {
    }

    private final static int MAX_PORT = 49151;
    private final static List<String> TABLE_ROWS;

    static {
        TABLE_ROWS = new ArrayList<>();
        TABLE_ROWS.add(String.format("%-10s %-6s %-40s\n", "Протокол", "Порт", "Сервис"));
    }

    private final static Map<Integer, String> PORTS;

    static {
        PORTS = new HashMap<>();
        PORTS.put(21, "FTP");
        PORTS.put(22, "SSH");
        PORTS.put(25, "SMTP");
        PORTS.put(53, "DNS");
        PORTS.put(80, "HTTP");
        PORTS.put(135, "EPMAP");
        PORTS.put(137, "Служба имен NetBIOS");
        PORTS.put(139, "Служба сеансов NetBIOS");
        PORTS.put(443, "HTTPS");
        PORTS.put(445, "Microsoft-DS Active Directory");
        PORTS.put(843, "Adobe Flash");
        PORTS.put(1900, "SSDP");
        PORTS.put(3702, "Динамическое обнаружение веб-служб");
        PORTS.put(5353, "Многоадресный DNS");
        PORTS.put(17500, "Dropbox");
        PORTS.put(27017, "MongoDB");
    }

    private enum Protocol {
        TCP,
        UDP
    }

    public static List<String> scanPorts() throws SocketException {
        for (int i = 0; i <= MAX_PORT; i++) {
            scanTcpPort(i);
            scanUdpPort(i);
        }
        return TABLE_ROWS;
    }

    private static void scanUdpPort(int port) throws SocketException {
        try (DatagramSocket ignored = new DatagramSocket(port)) {
        } catch (Exception e) {
            String service = getService(port);
            addResult(Protocol.UDP, port, service);
        }
    }

    private static void scanTcpPort(int port) throws SocketException {
        try (ServerSocket ignored = new ServerSocket(port)) {
        } catch (Exception e) {
            String service = getService(port);
            addResult(Protocol.TCP, port, service);
        }
    }

    private static void addResult(Protocol protocol, int port, String service) {
        TABLE_ROWS.add(String.format("%-10s %-6d %-40s\n", protocol, port, service));
    }

    private static String getService(int port) {
        String service = PORTS.get(port);
        if (service == null) {
            service = "Undefined";
        }
        return service;
    }
}
