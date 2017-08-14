package apihelper.interfaces;

import apihelper.pojo.AccountPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AccountApiClient {
    @GET("/account/api/v2/accounts/{id}")
    Call<AccountPojo> getAccountById(@Path("id") long id);
}
