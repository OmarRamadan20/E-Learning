package com.example.data.api.datasource;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\b\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/data/api/datasource/AuthDataSourceImpl;", "Lcom/example/data/api/contract/AuthDataSource;", "webServices", "Lcom/example/data/api/WebServices;", "(Lcom/example/data/api/WebServices;)V", "signIn", "Lcom/example/domain/common/MyResult;", "Lcom/example/domain/models/LoginResponse;", "request", "Lcom/example/domain/models/LoginRequest;", "(Lcom/example/domain/models/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signUp", "Lcom/example/domain/models/RegisterResponse;", "Lcom/example/domain/models/RegisterRequest;", "(Lcom/example/domain/models/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class AuthDataSourceImpl implements com.example.data.api.contract.AuthDataSource {
    @org.jetbrains.annotations.NotNull
    private final com.example.data.api.WebServices webServices = null;
    
    @javax.inject.Inject
    public AuthDataSourceImpl(@org.jetbrains.annotations.NotNull
    com.example.data.api.WebServices webServices) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object signUp(@org.jetbrains.annotations.NotNull
    com.example.domain.models.RegisterRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.domain.common.MyResult<com.example.domain.models.RegisterResponse>> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object signIn(@org.jetbrains.annotations.NotNull
    com.example.domain.models.LoginRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.domain.common.MyResult<com.example.domain.models.LoginResponse>> $completion) {
        return null;
    }
}