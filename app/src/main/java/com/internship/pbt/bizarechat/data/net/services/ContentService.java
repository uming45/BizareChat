package com.internship.pbt.bizarechat.data.net.services;


import com.internship.pbt.bizarechat.data.datamodel.response.CreateFileResponse;
import com.internship.pbt.bizarechat.data.datamodel.response.UploadFileResponse;
import com.internship.pbt.bizarechat.data.net.ApiConstants;
import com.internship.pbt.bizarechat.data.net.requests.FileCreateRequest;
import com.internship.pbt.bizarechat.data.net.requests.FileUploadConfirmRequest;
import com.internship.pbt.bizarechat.data.net.JsonAndXmlConverters.Xml;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;



public interface ContentService {
    @Headers({"Content-Type: application/json", "QuickBlox-REST-API-Version: 0.1.0"})
    @POST("/blobs.json")
    Observable<CreateFileResponse> createFile(@Header(ApiConstants.TOKEN_HEADER_NAME) String tokenHeader,
                                              @Body FileCreateRequest request);

    @Multipart
    @POST
    @Xml
    Observable<UploadFileResponse> uploadFile(@Url String url,
                                              @PartMap Map<String, RequestBody> params,
                                              @Part MultipartBody.Part file);

    @Headers({"Content-Type: application/json", "QuickBlox-REST-API-Version: 0.1.0"})
    @PUT("/blobs/{blob_id}/complete.json")
    Observable<Response<Void>> confirmFileUploaded(@Header(ApiConstants.TOKEN_HEADER_NAME) String tokenHeader,
                                                   @Path(value = "blob_id") String blobId,
                                                   @Body FileUploadConfirmRequest request);
}
