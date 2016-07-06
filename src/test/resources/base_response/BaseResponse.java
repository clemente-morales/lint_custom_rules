import android.os.Parcel;
import android.os.Parcelable;

public class BaseResponse implements Parcelable {
    private String pageType;
    private String header;
    protected String presentationStyle;

    public BaseResponse(String pageType, String header, String presentationStyle) {
        this.pageType = pageType;
        this.header = header;
        this.presentationStyle = presentationStyle;
        this.pageModel = new PageModel(pageType, header, presentationStyle);
    }
    
    protected BaseResponse(Parcel in) {
        this.pageType = in.readString();
        this.header = in.readString();
        this.presentationStyle = in.readString();
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pageType);
        dest.writeString(this.header);
        dest.writeString(this.presentationStyle);
    }
    
}
