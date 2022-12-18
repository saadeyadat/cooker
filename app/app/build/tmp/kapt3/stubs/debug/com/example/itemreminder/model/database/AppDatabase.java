package com.example.cooker.model.database;

import java.lang.System;

@androidx.room.TypeConverters(value = {com.example.cooker.model.Converters.class})
@androidx.room.Database(entities = {com.example.cooker.model.Item.class, com.example.cooker.model.User.class, com.example.cooker.model.Lists.class}, version = 1)
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\n"}, d2 = {"Lcom/example/itemreminder/model/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "getItemDao", "Lcom/example/itemreminder/model/database/ItemsDao;", "getListsDao", "Lcom/example/itemreminder/model/database/ListsDao;", "getUserDao", "Lcom/example/itemreminder/model/database/UsersDao;", "Companion", "app_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.cooker.model.database.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.cooker.model.database.ItemsDao getItemDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.cooker.model.database.UsersDao getUserDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.cooker.model.database.ListsDao getListsDao();
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/itemreminder/model/database/AppDatabase$Companion;", "", "()V", "getDatabase", "Lcom/example/itemreminder/model/database/AppDatabase;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.cooker.model.database.AppDatabase getDatabase(@org.jetbrains.annotations.Nullable()
        android.content.Context context) {
            return null;
        }
    }
}