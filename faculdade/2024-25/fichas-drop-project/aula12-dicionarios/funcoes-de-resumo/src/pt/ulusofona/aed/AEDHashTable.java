package pt.ulusofona.aed;

import java.util.LinkedList;

class AEDHashTablePair {
    String key;
    Object obj;

    public AEDHashTablePair(String key, Object obj) {
        this.key = key;
        this.obj = obj;
    }
}

class AEDHashTableFunctions {

    static void put(AEDHashTable table, String key, String value) {
        if(table.nrElements >= table.maxLoadFactor * table.data.length) {
            grow(table);
        }

        int hash = hash(table, key);

        LinkedList<AEDHashTablePair> objects;

        if(table.data[hash] == null) {
            objects = new LinkedList<AEDHashTablePair>();
            table.data[hash] = objects;
        } else {
            objects = table.data[hash];
        }

        objects.add(new AEDHashTablePair(key, value));

        table.nrElements++;
    }

    public static Object get(AEDHashTable table, String key) {
        int hash = hash(table, key);
        Object result = null;
        if(table.data[hash] != null) {
            LinkedList<AEDHashTablePair> objects = table.data[hash];
            int i = 0;
            while (i < objects.size()) {
                AEDHashTablePair pair = objects.get(i);
                if(pair.key.equals(key)) {
                    return pair.obj;
                }
                i++;
            }
        }
        return result;
    }

    private static void grow(AEDHashTable table) {
        int newSize = table.data.length * 2 + 1;
        LinkedList[] newData = new LinkedList[newSize];

        // growing implies re-hashing the whole dataset...
        for(int i = 0; i < table.data.length; i++) {
            if(table.data[i] != null) {
                LinkedList<AEDHashTablePair> objects = table.data[i];
                int j = 0;
                while (j < objects.size()) {
                    AEDHashTablePair pair = objects.get(j);
                    String key = pair.key;
                    int hash = hash(key, newSize); // re-hash to newSize
                    LinkedList newObjects;
                    if(newData[hash] == null) {
                        newObjects = new LinkedList<AEDHashTablePair>();
                        newData[hash] = newObjects;
                    } else {
                        newObjects = newData[hash];
                    }
                    newObjects.add(pair);
                    j++;
                }
            }
        }
        table.data = newData;
    }

    public static void debug(AEDHashTable table) {
        int ocupMaiorBucket = 0, vazios = 0;
        int nrElementos = 0;
        for(int i = 0; i < table.data.length; i++) {
            if(table.data[i] != null) {
                int size = table.data[i].size();
                nrElementos = nrElementos + size;
                System.out.println("Bucket " + i + " => " + size);
                if(size > ocupMaiorBucket) {
                    ocupMaiorBucket = size;
                }
            } else {
                System.out.println("Bucket " + i + " => " + 0);
                vazios++;
            }
        }
        double ocupMedia = (double) nrElementos / (table.data.length - vazios);
        System.out.println("Tamanho array (final) " + table.data.length);
        System.out.println("Bucket Vazios " + vazios);
        System.out.println("Maior Bucket " + ocupMaiorBucket);
        System.out.println("Ocup. Media (nao vazios) " + ocupMedia);
    }

    static int hash(AEDHashTable table, String key) {
        return hash(key, table.data.length);
    }


    //////////////////////////////////////
    // AREA QUE O ALUNO ALTERA
    //////////////////////////////////////

    static int hash(String key, int tableLength) {
        // ALTERE ESTA FN PARA INVOCAR A "funcao de resumo" que pretende activar (p.e. hash1(...))
        return hash1(key, tableLength);
    }

    static int hash1(String key, int tableLength) {
        if (key.isEmpty()) {
            return 0;
        }

        return (int) key.charAt(0) % tableLength;
    }

    static int hash2(String key, int tableLength) {
        int soma = 0;

        for (int i = 0; i < key.length(); i++) {
            soma += key.charAt(i);
        }

        return soma % tableLength;
    }

    static int hash3(String key, int tableLength) {
        int hash = 0;

        for (int i = 0; i < key.length(); i++) {
            hash = 31 * hash + key.charAt(i);
        }

        return Math.abs(hash) % tableLength;
    }
}


public class AEDHashTable {

    LinkedList[] data;
    int nrElements;
    float maxLoadFactor = 0.75f;

    public AEDHashTable() {
        data = new LinkedList[11];
        nrElements = 0;
    }
}