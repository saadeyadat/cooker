package com.example.cooker.view.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0011H\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0002J\u0012\u0010\u001c\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0019H\u0002J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010$\u001a\u00020\u0011H\u0002J\b\u0010%\u001a\u00020\u0011H\u0002J\b\u0010&\u001a\u00020\u0011H\u0002J\b\u0010\'\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u00a8\u0006("}, d2 = {"Lcom/example/itemreminder/view/activities/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "firebase", "Lcom/google/firebase/auth/FirebaseAuth;", "googleContent", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "sharedPreferences", "Landroid/content/SharedPreferences;", "usersViewModel", "Lcom/example/itemreminder/viewModel/UsersViewModel;", "getUsersViewModel", "()Lcom/example/itemreminder/viewModel/UsersViewModel;", "usersViewModel$delegate", "Lkotlin/Lazy;", "checkIntent", "", "content", "Landroidx/activity/result/ActivityResult;", "checkUser", "googleSignInAccount", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "displayToast", "text", "", "googleSignIn", "lastSigning", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openApp", "email", "regToDatabase", "regToFirebase", "regToSharedPref", "regularSignIn", "setGoogleContent", "setSharedPref", "signInButtons", "app_debug"})
public final class LoginActivity extends androidx.appcompat.app.AppCompatActivity {
    private final com.google.firebase.auth.FirebaseAuth firebase = null;
    private android.content.SharedPreferences sharedPreferences;
    private final kotlin.Lazy usersViewModel$delegate = null;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> googleContent;
    private java.util.HashMap _$_findViewCache;
    
    public LoginActivity() {
        super();
    }
    
    private final com.example.cooker.viewModel.UsersViewModel getUsersViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setSharedPref() {
    }
    
    private final void setGoogleContent() {
    }
    
    private final void lastSigning() {
    }
    
    private final void signInButtons() {
    }
    
    private final void regularSignIn() {
    }
    
    private final void openApp(java.lang.String email) {
    }
    
    private final void googleSignIn() {
    }
    
    private final void checkIntent(androidx.activity.result.ActivityResult content) {
    }
    
    private final void checkUser(com.google.android.gms.auth.api.signin.GoogleSignInAccount googleSignInAccount) {
    }
    
    private final void regToFirebase(com.google.android.gms.auth.api.signin.GoogleSignInAccount googleSignInAccount) {
    }
    
    private final void regToSharedPref(com.google.android.gms.auth.api.signin.GoogleSignInAccount googleSignInAccount) {
    }
    
    private final void regToDatabase(com.google.android.gms.auth.api.signin.GoogleSignInAccount googleSignInAccount) {
    }
    
    private final void displayToast(java.lang.String text) {
    }
}