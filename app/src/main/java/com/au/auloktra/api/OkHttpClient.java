package com.au.auloktra.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.au.auloktra.utils.LogHelper;


import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * Client working on HTTP layer to make network calls.
 */
public class OkHttpClient {

    private static final String TAG = OkHttpClient.class.getSimpleName();

    private static final MediaType IMAGE = MediaType.parse("image/png");
    private static final MediaType TEXT = MediaType.parse("text/plain");
    private static final MediaType JSON = MediaType.parse("application/json");

    private static final String HEADER_ENCODING = "Content-Encoding";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String ENCODING_GZIP = "gzip";

    private okhttp3.OkHttpClient client;
    private static OkHttpClient okHttpClient;


    // private constructor to keep class a singleton.
    private OkHttpClient() {
        client = new okhttp3.OkHttpClient.Builder().addInterceptor(getInterceptor(true)).build();
    }

    @NonNull
    private Interceptor getInterceptor(final boolean addBasicAuth) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request oldRequest = chain.request();
                Request.Builder builder = oldRequest.newBuilder();

                // Add headers only if the strings exist, otherwise do not.
                // There are all request common headers which we have to add in every request

                // TODO : TBD
                //builder.addHeader() - use to add app specific headers

                if (addBasicAuth) {
                    // After authentication is complete from server side will need to add some kind
                    // of access token.
                }

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        };
    }


    private RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public long contentLength() {
                return -1; // We don't know the compressed length in advance!
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }

    /**
     * Initialize and keep OkHttp instance
     * @param context context
     */
    public static synchronized void init(@NonNull Context context) {
        if (okHttpClient == null) {
            LogHelper.logInfo(TAG, "Initializing new OkHttp instance for http api calls.");
            okHttpClient = new OkHttpClient();
        }
    }

    @NonNull
    public static synchronized OkHttpClient getInstance() {
        if (okHttpClient == null) {
            LogHelper.logInfo(TAG, "Creating new OkHttp instance for http api calls.");
            okHttpClient = new OkHttpClient();
        }
        return okHttpClient;
    }

    /**
     * Get wrapped OkHttp3 client to make requests.
     */
    @NonNull
    public okhttp3.OkHttpClient getClient() {
        return client;
    }

    /**
     * Use this to change mode to intercept header and add params dictated by the booleans.
     *
     * @param addBasicAuth Add basic authentication in header.
     */
    public void changeInterceptor(boolean addBasicAuth) {
        client.newBuilder().addInterceptor(getInterceptor(addBasicAuth));
    }

    /**
     * Sign in after user logs in
     *
     * @param postBodyParams body params key value map
     * @return Live data observable
     */
    /*public LiveData<Resource<SignInResponseModel>> signIn(@NonNull Map<String, String> postBodyParams) {

        LogHelper.logInfo(TAG, "sign in api called");
        final ThreadSafeLiveData<Resource<SignInResponseModel>> resource = new ThreadSafeLiveData<>();
        Resource<SignInResponseModel> loading = Resource.loading(null);
        resource.setValue(loading);

        RequestBody body = RequestBody.create(JSON, OkHttpUtils.jsonStringForMap(postBodyParams));
        Request request = new Request.Builder().url(AppConstants.LOGIN_URL).post(body).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                LogHelper.logInfo(TAG, "onFailure for request: " + call.request() + ", request could not be completed due to connectivity issue");
                sendSignInError(resource);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                int code = response.code();
                LogHelper.logInfo(TAG, "Response to sign in arrived with code " + code);

                switch (code) {
                    case 200:
                        String responseJsonString = response.body().string();
                        String userId = "";
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(responseJsonString);
                            if(jsonObject.has("errors")){
                                sendSignInError(resource);
                                return;
                            }
                            userId=jsonObject.getJSONObject("results").getString("user_id");
                            SharedPrefUtil.updateUserData("userId",userId);

                        }catch (Exception e){};
                        SignInResponseModelWrapper signInResponseModel =
                                GsonHelper.getInstance()
                                        .fromJson(responseJsonString, SignInResponseModelWrapper.class);

                        LogHelper.logInfo(TAG, "200 OK response for sign in body : " + responseJsonString);
                        SharedPrefUtil.saveStringToPreferences(SharedPrefConstants.USER_ID, signInResponseModel.signInResponse.userId);
                        resource.setValue(Resource.success(signInResponseModel.signInResponse));
                        break;
                    case 400:
                        break;
                    default:
                        sendSignInError(resource);
                        break;
                }
            }
        });
        return resource;
    }

    private void sendSignInError(@NonNull final MutableLiveData<Resource<SignInResponseModel>> error) {
        LogHelper.logInfo(TAG, ">>> Test code called");
        Resource<SignInResponseModel> res = Resource.error("TBD, this is tags_background message", null);
        error.setValue(res);
    }*/

}
