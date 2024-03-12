package test.java.org.service.impl;

import Mapping.ToyDTO;
import Model.Toy;
import com.sun.jdi.Type;
import org.junit.Before;
import org.junit.Test;
import services.implement.ToyServiceImpl;

import java.awt.*;
import java.awt.List;
import java.util.*;

import static org.junit.Assert.*;

public class ToyServiceImplTest {

    private ToyServiceImpl service;

    @Before
    public void setup(){
        service = new ToyServiceImpl();
    }

    @Test
    public void addToy_Successful() throws Exception {
        String name = "Cat";
        Model.Type type = Model.Type.M;
        Integer price = 200000;
        Integer amount = 4;
        ToyDTO toyToAdd = new ToyDTO(name,type,price,amount);
        List expected = (List) Collections.singletonList(toyToAdd);
        List result = (List) service.addToy(toyToAdd);
        assertEquals(expected,result);
    }
    @Test
    public void addToy_Exception() throws Exception {
        String name = "Cat";
        Model.Type type = Model.Type.M;
        Integer price = 200000;
        Integer amount = 3;
        ToyDTO toyToAdd = new ToyDTO(name,type,price,amount);
        List expected = (List) Collections.singletonList(toyToAdd);
        List result = (List) service.addToy(toyToAdd);
        assertEquals(expected,result);
        assertThrows(Exception.class,()->service.addToy(toyToAdd));
    }

    @Test
    public void list_Successful(){
        List toyStoreDTOList = (List) service.listToys();
        assertNotNull(toyStoreDTOList);
        assertFalse(toyStoreDTOList.size());
    }

    @Test
    public void search_Successful() throws Exception {
        String name = "Cat";
        Model.Type type = Model.Type.M;
        Integer price = 200000;
        Integer amount = 3;
        ToyDTO toyExpected = new ToyDTO(name,type,price,amount);
        Class<? extends java.util.List> result = service.search(name);
        assertEquals(toyExpected,result);
    }

    @Test
    public void search_Exception() throws Exception {
        String name = "Catt";
        assertThrows(Exception.class,()->service.search(name));
    }
    @Test
    public void maxToy_Successful() throws Exception {
        Map<Type, Integer> testMap = Map.of(Model.Type.M, 30, Model.Type.F, 10, Model.Type.U, 50);
        Map.Entry<Type,Integer> expected = testMap.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
        Map.Entry<Type,Integer> result = service.maxToy();
        assertEquals(expected,result);

    }

    @Test
    public void minToy_Successful()throws Exception{
        Map<Type, Integer> testMap = Map.of(Model.Type.M,30, Model.Type.F,10, Model.Type.U,50);
        Map.Entry<Type,Integer> expected = testMap.entrySet().stream().min(Map.Entry.comparingByValue()).orElse(null);
        Map.Entry<Type,Integer> result = service.minToy();
        assertEquals(expected,result);

    }

    @Test
    public void increase_Successful()throws Exception{
        String name = "Cat";
        Type type = Model.Type.M;
        Integer price = 200000;
        int amount = 3;
        int newAmount = 4;
        ToyDTO toyToAdd = new ToyDTO(name, (java.lang.reflect.Type) type,price,amount+newAmount);
        List listExpected = (List) Collections.singletonList(toyToAdd);
        List result = (List) service.increase(toyToAdd,newAmount);
        assertEquals(listExpected,result);

    }

    @Test
    public void increase_Exception()throws Exception{
        String name = "Cat";
        Model.Type type = Model.Type.M;
        Integer price = 200000;
        int amount = 3;
        int newAmount = 4;
        ToyDTO toyToAdd = new ToyDTO(name,type,price,amount+newAmount);
        assertThrows(Exception.class,()->service.increase(toyToAdd,newAmount));

    }
    @Test
    public void decrease_Successful() throws Exception{
        String name = "Cat";
        Model.Type type = Model.Type.M;
        Integer price = 200000;
        int amount = 4;
        int newAmount = 1;
        ToyDTO toyToAdd = new ToyDTO(name,type,price,amount-newAmount);
        List listExpected = (List) Collections.singletonList(toyToAdd);
        List result = (List) service.decrease(toyToAdd,newAmount);
        assertEquals(listExpected,result);

    }
    @Test
    public void showByType_Successful()throws Exception{
        Map<Type,Integer> mapExpected = new TreeMap<>();
        mapExpected.put(Model.Type.M,1);
        Map<Type,Integer> result = service.showByType();
        assertEquals(mapExpected,result);
    }

    @Test
    public void showByType_Exception()throws Exception{
        service.setToyList(null);
        assertThrows(Exception.class,()->service.showByType());
    }

    @Test
    public void sort_Successful() throws Exception {
        Map<Type,Integer> unsortedMap = new HashMap<>();
        unsortedMap.put(Model.Type.F,3);
        unsortedMap.put(Model.Type.M,1);
        unsortedMap.put(Model.Type.U,2);
        Map<Type,Integer> expectedMap = new HashMap<>();
        expectedMap.put(Model.Type.M,1);
        expectedMap.put(Model.Type.U,2);
        expectedMap.put(Model.Type.F,3);
        Map<Type,Integer> result = service.sort();
        assertEquals(expectedMap,result);
    }

    @Test
    public void sort_Exception() throws Exception {
        Map<Type,Integer> unsortedMap = new HashMap<>();
        unsortedMap.put(Model.Type.F,3);
        unsortedMap.put(Model.Type.M,1);
        unsortedMap.put(Model.Type.U,2);
        Map<Type,Integer> expectedMap = new HashMap<>();
        expectedMap.put(Model.Type.M,1);
        expectedMap.put(Model.Type.U,2);
        expectedMap.put(Model.Type.F,3);

        assertThrows(Exception.class,()->service.sort());
    }

    @Test
    public void showToysAbove_Successful() throws Exception {
        List expectedList = new List();
        expectedList.add(String.valueOf(new Toy("cow", Model.Type.U,100,7)));
        expectedList.add(String.valueOf(new Toy("horse", Model.Type.U,150,9)));
        service.setToyList((java.util.List<Toy>) expectedList);
        int value = 100;
        List result = (List) service.showToysAbove(value);
        for (ToyDTO toyDTO : result) {
            assertTrue(toyDTO.price() >= value);
        }
    }

    @Test
    public void showToysAbove_Exception() throws Exception {
        List expectedList = new ArrayList<>();
        service.setToyList((java.util.List<Toy>) expectedList);
        int value = 100;
        assertThrows(Exception.class,()->service.showToysAbove(value));
    }

    @Test
    public void verifyExist_True(){
        ToyDTO toyStoreDTO = new ToyDTO("rex", Model.Type.U,1200,10);
        assertTrue(service.verifyExist(toyStoreDTO.name()));
    }
    @Test
    public void verifyExist_False(){
        assertFalse(service.verifyExist("coww"));
    }

    @Test
    public void totalToys_Successful() throws Exception {
        service.addToy(new ToyDTO("chicken", Model.Type.U,1200,10));
        service.addToy(new ToyDTO("bike", Model.Type.M,2000,8));
        int expectedTotal = 10+8;
        int result = service.totalToys();
        assertEquals(expectedTotal,result);
    }

    @Test
    public void totalPrices_Successful() throws Exception{
        service.addToy(new ToyDTO("doll", Model.Type.U,1200,10));
        service.addToy(new ToyDTO("car", Model.Type.M,2000,8));
        int expectedTotal = 1200+2000;
        int result = service.totalPriceAllToys();
        assertEquals(expectedTotal,result);
    }

}
