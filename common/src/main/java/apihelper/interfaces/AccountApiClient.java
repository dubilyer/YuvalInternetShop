package apihelper.interfaces;

import apihelper.pojo.AccountPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AccountApiClient {
    @GET("/account/api/v2/accounts/id/{id}")
    Call<AccountPojo> getAccountById(@Path("id") long id);

    @GET("account/api/v2/accounts/name/{name}")
    Call<AccountPojo> getAccountByName(@Path("name") String name);
}
