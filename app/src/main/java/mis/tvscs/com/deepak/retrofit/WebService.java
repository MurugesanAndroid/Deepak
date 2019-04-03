package mis.tvscs.com.deepak.retrofit;

import mis.tvscs.com.deepak.model.Model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebService {

    @GET("iranjith4/radius-intern-mobile/master/users.json")
    Call<Model> gerDatafromServer();
}
