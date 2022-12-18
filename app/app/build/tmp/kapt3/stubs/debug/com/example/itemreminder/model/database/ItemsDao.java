package com.example.cooker.model.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u000b"}, d2 = {"Lcom/example/itemreminder/model/database/ItemsDao;", "", "addItem", "", "item", "Lcom/example/itemreminder/model/Item;", "deleteItem", "getAllItems", "Landroidx/lifecycle/LiveData;", "", "updateItem", "app_debug"})
public abstract interface ItemsDao {
    
    @androidx.room.Insert()
    public abstract void addItem(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Item item);
    
    @androidx.room.Delete()
    public abstract void deleteItem(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Item item);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "Select * from fruitsTable")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.cooker.model.Item>> getAllItems();
    
    @androidx.room.Update()
    public abstract void updateItem(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Item item);
}