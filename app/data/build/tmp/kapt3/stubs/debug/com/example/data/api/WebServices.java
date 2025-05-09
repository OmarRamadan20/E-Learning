package com.example.data.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J}\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\u000b2\b\b\u0001\u0010\r\u001a\u00020\u000b2\b\b\u0001\u0010\u000e\u001a\u00020\u000b2\b\b\u0001\u0010\u000f\u001a\u00020\u000b2\b\b\u0001\u0010\u0010\u001a\u00020\u000b2\b\b\u0001\u0010\u0011\u001a\u00020\u000b2\b\b\u0001\u0010\u0012\u001a\u00020\u000b2\b\b\u0001\u0010\u0013\u001a\u00020\u000b2\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/example/data/api/WebServices;", "", "signIn", "Lretrofit2/Response;", "Lcom/example/domain/models/LoginResponse;", "request", "Lcom/example/domain/models/LoginRequest;", "(Lcom/example/domain/models/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signUp", "Lcom/example/domain/models/RegisterResponse;", "email", "Lokhttp3/RequestBody;", "password", "firstName", "lastName", "dob", "sex", "mobileNumber", "city", "gov", "profilePicture", "Lokhttp3/MultipartBody$Part;", "(Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public abstract interface WebServices {
    
    @retrofit2.http.Multipart
    @retrofit2.http.POST(value = "auth/sign_up")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object signUp(@retrofit2.http.Part(value = "email")
    @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody email, @retrofit2.http.Part(value = "password")
    @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody password, @retrofit2.http.Part(value = "firstName")
    @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody firstName, @retrofit2.http.Part(value = "lastName")
    @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody lastName, @retrofit2.http.Part(value = "DOB")
    @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody dob, @retrofit2.http.Part(value = "sex")
    @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody sex, @retrofit2.http.Part(value = "mobileNumber")
    @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody mobileNumber, @retrofit2.http.Part(value = "city")
    @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody city, @retrofit2.http.Part(value = "GOV")
    @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody gov, @retrofit2.http.Part
    @org.jetbrains.annotations.Nullable
    okhttp3.MultipartBody.Part profilePicture, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.domain.models.RegisterResponse>> $completion);
    
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object signIn(@retrofit2.http.Body
    @org.jetbrains.annotations.NotNull
    com.example.domain.models.LoginRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.domain.models.LoginResponse>> $completion);
}