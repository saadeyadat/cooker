package com.example.cooker.model.database;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u000f\u0018\u0000 12\u00020\u0001:\u00011B\u0011\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0019J\u0016\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0016J\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010 \u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013J\u0012\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130#0\"J\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190#0\"J\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100#0\"J\u000e\u0010&\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u0019J\u0010\u0010\'\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0016\u0010(\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u0016J\u0016\u0010*\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\u0016J\u0010\u0010,\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0016\u0010-\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u0016J\u0010\u0010/\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0016\u00100\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/example/itemreminder/model/database/Repository;", "", "application", "Landroid/content/Context;", "(Landroid/content/Context;)V", "firebase", "Lcom/example/itemreminder/other/managers/FirebaseManager;", "itemDao", "Lcom/example/itemreminder/model/database/ItemsDao;", "listsDao", "Lcom/example/itemreminder/model/database/ListsDao;", "userDao", "Lcom/example/itemreminder/model/database/UsersDao;", "addItem", "", "item", "Lcom/example/itemreminder/model/Item;", "addList", "list", "Lcom/example/itemreminder/model/Lists;", "addParticipant", "participant", "", "addUser", "user", "Lcom/example/itemreminder/model/User;", "addUserImageToFB", "uri", "Landroid/net/Uri;", "currentUser", "addUserList", "deleteItem", "deleteList", "getAllLists", "Landroidx/lifecycle/LiveData;", "", "getAllUsers", "getLiveDataAllItems", "getUserImageToFB", "updateItem", "updateItemImage", "image", "updateItemInfo", "info", "updateList", "updateParticipants", "participants", "updateUser", "updateUserImage", "Companion", "app_debug"})
public final class Repository {
    private final com.example.cooker.model.database.ItemsDao itemDao = null;
    private final com.example.cooker.model.database.UsersDao userDao = null;
    private final com.example.cooker.model.database.ListsDao listsDao = null;
    private final com.example.cooker.other.managers.FirebaseManager firebase = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.cooker.model.database.Repository.Companion Companion = null;
    private static com.example.cooker.model.database.Repository instance;
    
    private Repository(android.content.Context application) {
        super();
    }
    
    public final void addUser(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.User user) {
    }
    
    public final void addUserList(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.User user, @org.jetbrains.annotations.NotNull()
    java.lang.String list) {
    }
    
    private final void updateUser(com.example.cooker.model.User user) {
    }
    
    public final void updateUserImage(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.User user, @org.jetbrains.annotations.NotNull()
    java.lang.String image) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.cooker.model.User>> getAllUsers() {
        return null;
    }
    
    public final void addUserImageToFB(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    com.example.cooker.model.User currentUser) {
    }
    
    public final void getUserImageToFB(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.User currentUser) {
    }
    
    public final void addList(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Lists list) {
    }
    
    public final void deleteList(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Lists list) {
    }
    
    private final void updateList(com.example.cooker.model.Lists list) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.cooker.model.Lists>> getAllLists() {
        return null;
    }
    
    public final void addParticipant(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Lists list, @org.jetbrains.annotations.NotNull()
    java.lang.String participant) {
    }
    
    public final void updateParticipants(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Lists list, @org.jetbrains.annotations.NotNull()
    java.lang.String participants) {
    }
    
    public final void addItem(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Item item) {
    }
    
    public final void deleteItem(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Item item) {
    }
    
    private final void updateItem(com.example.cooker.model.Item item) {
    }
    
    public final void updateItemInfo(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Item item, @org.jetbrains.annotations.NotNull()
    java.lang.String info) {
    }
    
    public final void updateItemImage(@org.jetbrains.annotations.NotNull()
    com.example.cooker.model.Item item, @org.jetbrains.annotations.NotNull()
    java.lang.String image) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.cooker.model.Item>> getLiveDataAllItems() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/itemreminder/model/database/Repository$Companion;", "", "()V", "instance", "Lcom/example/itemreminder/model/database/Repository;", "getInstance", "application", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.cooker.model.database.Repository getInstance(@org.jetbrains.annotations.Nullable()
        android.content.Context application) {
            return null;
        }
    }
}