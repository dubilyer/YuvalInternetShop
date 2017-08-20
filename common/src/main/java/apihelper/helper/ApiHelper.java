package apihelper.helper;

import apihelper.interfaces.AccountApiClient;
import apihelper.pojo.AccountPojo;
import config.Constants;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;


public class ApiHelper {
    private static AccountApiClient accountApiClient;
    private Retrofit retrofit;

    public ApiHelper() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Constants.PROJECT_BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        accountApiClient = retrofit.create(AccountApiClient.class);
    }

    public AccountPojo getAccountById(long id) throws IOException {
        Response<AccountPojo> response = accountApiClient.getAccountById(id).execute();
        return response.body();
    }

    public AccountPojo getAccountByName(String name) throws IOException {
        Response<AccountPojo> response = accountApiClient.getAccountByName(name).execute();
        return response.body();
    }
}
