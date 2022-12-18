package com.example.cooker.view.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002J\u0010\u00104\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002J\u0010\u00105\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002J\u0010\u00106\u001a\u0002012\u0006\u00107\u001a\u000208H\u0002J\u0010\u00109\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002J\u0010\u0010:\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002J\u0010\u0010;\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002J\u0010\u0010<\u001a\u0002012\u0006\u0010=\u001a\u00020\u0012H\u0002J\u0010\u0010>\u001a\u0002012\u0006\u00107\u001a\u000208H\u0002J\u0010\u0010?\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002J\"\u0010@\u001a\u0002012\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020B2\b\u0010D\u001a\u0004\u0018\u00010\u001dH\u0014J\u0012\u0010E\u001a\u0002012\b\u0010F\u001a\u0004\u0018\u00010GH\u0014J-\u0010H\u001a\u0002012\u0006\u0010A\u001a\u00020B2\u000e\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00160J2\u0006\u0010K\u001a\u00020LH\u0016\u00a2\u0006\u0002\u0010MJ\u0010\u0010N\u001a\u0002012\u0006\u00107\u001a\u000208H\u0002J\u0010\u0010O\u001a\u0002012\u0006\u0010P\u001a\u00020\u0016H\u0002J\u0010\u0010Q\u001a\u0002012\u0006\u0010R\u001a\u00020\u0016H\u0002J#\u0010S\u001a\u001d\u0012\u0013\u0012\u00110\u0012\u00a2\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(=\u0012\u0004\u0012\u0002010TH\u0002J\u0010\u0010W\u001a\u0002012\u0006\u00107\u001a\u000208H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0000X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0010\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u001d0\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"R\u001b\u0010%\u001a\u00020&8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b)\u0010$\u001a\u0004\b\'\u0010(R\u001c\u0010*\u001a\u0010\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u001d0\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010+\u001a\u00020,8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b/\u0010$\u001a\u0004\b-\u0010.\u00a8\u0006X"}, d2 = {"Lcom/example/itemreminder/view/activities/ItemsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "allowCamera", "", "getAllowCamera", "()Z", "setAllowCamera", "(Z)V", "allowResult", "getAllowResult", "setAllowResult", "currentActivity", "getCurrentActivity", "()Lcom/example/itemreminder/view/activities/ItemsActivity;", "setCurrentActivity", "(Lcom/example/itemreminder/view/activities/ItemsActivity;)V", "currentItem", "Lcom/example/itemreminder/model/Item;", "currentUser", "Lcom/example/itemreminder/model/User;", "currentUserEmail", "", "getCurrentUserEmail", "()Ljava/lang/String;", "setCurrentUserEmail", "(Ljava/lang/String;)V", "itemContent", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "itemsViewModel", "Lcom/example/itemreminder/viewModel/ItemsViewModel;", "getItemsViewModel", "()Lcom/example/itemreminder/viewModel/ItemsViewModel;", "itemsViewModel$delegate", "Lkotlin/Lazy;", "listsViewModel", "Lcom/example/itemreminder/viewModel/ListsViewModel;", "getListsViewModel", "()Lcom/example/itemreminder/viewModel/ListsViewModel;", "listsViewModel$delegate", "userContent", "usersViewModel", "Lcom/example/itemreminder/viewModel/UsersViewModel;", "getUsersViewModel", "()Lcom/example/itemreminder/viewModel/UsersViewModel;", "usersViewModel$delegate", "addItem", "", "list", "Lcom/example/itemreminder/model/Lists;", "addParticipant", "addUserImage", "cameraAlert", "context", "Landroid/content/Context;", "clickListen", "deleteParticipant", "displayInfo", "displayItemFragment", "item", "itemImageAlert", "itemsRecyclerView", "onActivityResult", "requestCode", "", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onlyOwnerAllowedAlert", "participantsRecyclerView", "participants", "setList", "listID", "updateImage", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "userImageAlert", "app_debug"})
public final class ItemsActivity extends androidx.appcompat.app.AppCompatActivity {
    private boolean allowCamera = false;
    private boolean allowResult = false;
    @org.jetbrains.annotations.NotNull()
    private com.example.cooker.view.activities.ItemsActivity currentActivity;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentUserEmail = "";
    private final kotlin.Lazy itemsViewModel$delegate = null;
    private final kotlin.Lazy listsViewModel$delegate = null;
    private final kotlin.Lazy usersViewModel$delegate = null;
    private com.example.cooker.model.Item currentItem;
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> itemContent = null;
    private com.example.cooker.model.User currentUser;
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> userContent = null;
    private java.util.HashMap _$_findViewCache;
    
    public ItemsActivity() {
        super();
    }
    
    public final boolean getAllowCamera() {
        return false;
    }
    
    public final void setAllowCamera(boolean p0) {
    }
    
    public final boolean getAllowResult() {
        return false;
    }
    
    public final void setAllowResult(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.cooker.view.activities.ItemsActivity getCurrentActivity() {
        return null;
    }
    
    public final void setCurrentActivity(@org.jetbrains.annotations.NotNull()
    com.example.cooker.view.activities.ItemsActivity p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentUserEmail() {
        return null;
    }
    
    public final void setCurrentUserEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    private final com.example.cooker.viewModel.ItemsViewModel getItemsViewModel() {
        return null;
    }
    
    private final com.example.cooker.viewModel.ListsViewModel getListsViewModel() {
        return null;
    }
    
    private final com.example.cooker.viewModel.UsersViewModel getUsersViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setList(java.lang.String listID) {
    }
    
    private final void displayInfo(com.example.cooker.model.Lists list) {
    }
    
    private final void clickListen(com.example.cooker.model.Lists list) {
    }
    
    private final void addItem(com.example.cooker.model.Lists list) {
    }
    
    private final void addParticipant(com.example.cooker.model.Lists list) {
    }
    
    private final void deleteParticipant(com.example.cooker.model.Lists list) {
    }
    
    private final void addUserImage(com.example.cooker.model.Lists list) {
    }
    
    private final void participantsRecyclerView(java.lang.String participants) {
    }
    
    private final void itemsRecyclerView(com.example.cooker.model.Lists list) {
    }
    
    private final void displayItemFragment(com.example.cooker.model.Item item) {
    }
    
    private final kotlin.jvm.functions.Function1<com.example.cooker.model.Item, kotlin.Unit> updateImage() {
        return null;
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final void cameraAlert(android.content.Context context) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void itemImageAlert(android.content.Context context) {
    }
    
    private final void userImageAlert(android.content.Context context) {
    }
    
    private final void onlyOwnerAllowedAlert(android.content.Context context) {
    }
}