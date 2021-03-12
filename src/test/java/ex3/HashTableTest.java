package ex3;


import org.junit.jupiter.api.Assertions;

class HashTableTest {

    @org.junit.jupiter.api.Test
    void count() {
        //probar count
        HashTable hashTable = new HashTable();
        hashTable.put("12345","David");
        hashTable.put("89742","Ferrero");
        int count = 2;
        Assertions.assertEquals(count,hashTable.count());
    }

    @org.junit.jupiter.api.Test
    void size() {
    HashTable hashTable = new HashTable();
    int size = 16;
    Assertions.assertEquals(size,hashTable.size());
    }

    @org.junit.jupiter.api.Test
    void put()  {
        String key = "12345";
        String value ="David";
        String key2 = "89742";
        String value2 ="Ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);
        String esperado = "bucket[3] = [12345, David]\n" + "bucket[4] = [89742, Ferrero]\n";
        Assertions.assertEquals(esperado,hashTable.toString());
    }

    @org.junit.jupiter.api.Test
    void putSameKey()   {
        String key = "12345";
        String value ="David";
        String key2 = "12345";
        String value2 ="Ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);
        String esperado = "bucket[3] = [12345, Ferrero]\n";
        Assertions.assertEquals(esperado,hashTable.toString());

    }
    @org.junit.jupiter.api.Test
    void putColisions()   {
        String key = "10";
        String value ="David";
        String key2 = "21";
        String value2 ="Ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);
        String esperado = "bucket[15] = [10, David]\n" + " -> [21, Ferrero]\n";
        Assertions.assertEquals(esperado,hashTable.toString());

    }


    @org.junit.jupiter.api.Test
    void getNormal() {
        String key = "12345";
        String value ="David";
        String key2 = "21";
        String value2 ="ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);
        String esperado = "ferrero";
        Assertions.assertEquals(esperado,hashTable.get("21"));

    }
    @org.junit.jupiter.api.Test
    void getWithColisions() {
        String key = "0";
        String value ="David";
        String key2 = "11";
        String value2 ="ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);
        String esperado = "ferrero";
        Assertions.assertEquals(esperado,hashTable.get("11"));

    }
    @org.junit.jupiter.api.Test
    void getWithColisionsNoExiste() {
        String key = "0";
        String value ="David";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        String esperado = null;
        Assertions.assertEquals(esperado,hashTable.get("11"));

    }
    @org.junit.jupiter.api.Test
    void drop1elemento() {
        //Una key
        String key = "12345";
        String value ="David";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.drop("12345");
        String esperado = "";
        Assertions.assertEquals(esperado,hashTable.toString());
        int count = 0;
        Assertions.assertEquals(count,hashTable.count());
    }
    @org.junit.jupiter.api.Test
    void drop1elementoColisionNoExiste() {
        //Intentar borrar un elemento que no existe pero que provoca colision
        String key = "0";
        String value ="David";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.drop("11");
        String esperado = "bucket[0] = [0, David]\n";
        Assertions.assertEquals(esperado,hashTable.toString());
        int count = 1;
        Assertions.assertEquals(count,hashTable.count());
    }

    @org.junit.jupiter.api.Test
    void dropNormal() {
        //Dos key que no provocan colision
        String key = "12345";
        String value ="David";
        String key2 = "21";
        String value2 ="ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);

        hashTable.drop("12345");

        String esperado = "bucket[15] = [21, ferrero]\n";
        Assertions.assertEquals(esperado,hashTable.toString());
        int count = 1;
        Assertions.assertEquals(count,hashTable.count());
    }
    @org.junit.jupiter.api.Test
    void dropColision1() {
        //Tres key que provocan colision borrar el primero
        String key = "0";
        String value ="David";
        String key2 = "11";
        String value2 ="ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);
        hashTable.put("22","hola");
        hashTable.drop("0");
        String esperado ="bucket[0] = [11, ferrero]\n" + " -> [22, hola]\n";
        Assertions.assertEquals(esperado,hashTable.toString());
        int count = 2;
        Assertions.assertEquals(count,hashTable.count());
    }

    @org.junit.jupiter.api.Test
    void dropColision2() {
        //Tres key que provocan colision borrar el segundo
        String key = "0";
        String value ="David";
        String key2 = "11";
        String value2 ="ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);
        hashTable.put("22","hola");
        hashTable.drop("11");
        String esperado ="bucket[0] = [0, David]\n" + " -> [22, hola]\n";
        Assertions.assertEquals(esperado,hashTable.toString());
        int count = 2;
        Assertions.assertEquals(count,hashTable.count());
    }
    @org.junit.jupiter.api.Test
    void drop2ColisionesSeparados() {
        //hacer 2 drops de elementos que colisionan que no esten seguidos
        String key = "0";
        String value ="David";
        String key2 = "11";
        String value2 ="ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);
        hashTable.put("22","hola");

        hashTable.drop("0");
        hashTable.drop("22");

        String esperado ="bucket[0] = [11, ferrero]\n";
        Assertions.assertEquals(esperado,hashTable.toString());

    }
    @org.junit.jupiter.api.Test
    void drop2ColisionesJuntos() {
        //hacer 2 drops de elementos que colisionan que no esten seguidos
        String key = "0";
        String value ="David";
        String key2 = "11";
        String value2 ="ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);
        hashTable.put("22","hola");

        hashTable.drop("0");
        hashTable.drop("11");

        String esperado ="bucket[0] = [22, hola]\n";
        Assertions.assertEquals(esperado,hashTable.toString());

    }


    @org.junit.jupiter.api.Test
    void dropColision3() {
        //Tres key que provocan colision borrar el tercero
        String key = "0";
        String value ="David";
        String key2 = "11";
        String value2 ="ferrero";
        HashTable hashTable = new HashTable();
        hashTable.put(key,value);
        hashTable.put(key2,value2);
        hashTable.put("22","hola");
        hashTable.drop("22");
        String esperado ="bucket[0] = [0, David]\n" + " -> [11, ferrero]\n";
        Assertions.assertEquals(esperado,hashTable.toString());
        int count = 2;
        Assertions.assertEquals(count,hashTable.count());
    }

}
