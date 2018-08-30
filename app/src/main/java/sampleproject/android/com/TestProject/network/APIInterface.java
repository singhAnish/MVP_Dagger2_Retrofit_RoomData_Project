package sampleproject.android.com.TestProject.network;

import retrofit2.Call;
import retrofit2.http.GET;
import sampleproject.android.com.TestProject.BuildConfig;
import sampleproject.android.com.TestProject.model.WonderActivityModel;

public interface APIInterface {

    @GET(BuildConfig.KEY)
    Call<WonderActivityModel> getMovieListData();

}
