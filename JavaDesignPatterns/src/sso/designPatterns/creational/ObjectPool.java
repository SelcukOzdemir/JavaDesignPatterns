package sso.designPatterns.creational;

import java.util.LinkedList;

public class ObjectPool {}


class Connection {
    private String connectionName;

    public Connection(String name) {
        this.connectionName = name;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void connect() {
        System.out.println(connectionName + " connected.");
    }

    public void disconnect() {
        System.out.println(connectionName + " disconnected.");
    }
}

class ConnectionPool {
    private final LinkedList<Connection> pool;
    private final int MAX_POOL_SIZE = 3;

    public ConnectionPool() {
        pool = new LinkedList<>();
        // Başlangıçta bazı bağlantıları havuza ekleyelim
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            pool.add(new Connection("Connection-" + (i + 1)));
        }
    }

    // Bir bağlantı ödünç almak için metod
    public Connection borrowConnection() {
        if (pool.isEmpty()) {
            System.out.println("Havuzda kullanılabilir bağlantı yok, yeni bir bağlantı oluşturuluyor.");
            return new Connection("New-Connection");
        }
        return pool.removeFirst();  // Havuzdan bir bağlantı çıkar
    }

    // Bağlantıyı geri iade etmek için metod
    public void returnConnection(Connection connection) {
        if (pool.size() < MAX_POOL_SIZE) {
            pool.add(connection);  // Bağlantıyı havuza geri ekle
            System.out.println(connection.getConnectionName() + " returned to the pool.");
        }
    }
}

 class Main {
    public static void main(String[] args) {
ConnectionPool connectionPool = new ConnectionPool();
        
        // Bağlantı 1
        Connection connection1 = connectionPool.borrowConnection();
        connection1.connect();  // Bağlantıyı kullan
        connection1.disconnect();  // Bağlantıyı kapat
        connectionPool.returnConnection(connection1);  // Bağlantıyı geri ver
        
        // Bağlantı 2
        Connection connection2 = connectionPool.borrowConnection();
        connection2.connect();  // Bağlantıyı kullan
        connection2.disconnect();  // Bağlantıyı kapat
        connectionPool.returnConnection(connection2);  // Bağlantıyı geri ver
        
        // Yeni bir bağlantı almak
        Connection connection3 = connectionPool.borrowConnection();
        connection3.connect();  // Bağlantıyı kullan
        connection3.disconnect();  // Bağlantıyı kapat
        connectionPool.returnConnection(connection3);  // Bağlantıyı geri ver

        // Sonraki bağlantıyı almak (Connection-1 tekrar kullanılacak)
        Connection connection4 = connectionPool.borrowConnection();
        connection4.connect();
        connection4.disconnect();
        connectionPool.returnConnection(connection4);
    }
}
