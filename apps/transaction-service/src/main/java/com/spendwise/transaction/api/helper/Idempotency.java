package com.spendwise.transaction.api.helper;

import com.spendwise.transaction.api.dto.TransactionResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Idempotency {

    private record Entry(int bodyHash, TransactionResponse response){}
    private static final Map<String,Entry> CACHE = new ConcurrentHashMap<>();
    public static TransactionResponse lookup(String key, Object body){
        if(key == null) return null;
        Entry e = CACHE.get(key);
        if(e==null) return null;
        if(e.bodyHash() != body.hashCode()) throw new RuntimeException("Idempotency key is re-used with different body");
        return e.response();
    }
    public static void store(String key, Object body, TransactionResponse response){
        if(key != null) CACHE.put(key,new Entry(body.hashCode(), response));
    }

}
