public class ValidBaseResponse extends BaseResponse {
    public ValidBaseResponse(String pageType, String header, String presentationStyle) {
        super(pageType, header,presentationStyle);
    }

    public ValidBaseResponse(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public static final Creator<BillResponse> CREATOR = new Creator<BillResponse>() {
        @Override
        public BillResponse createFromParcel(Parcel source) {
            return new BillResponse(source);
        }

        @Override
        public BillResponse[] newArray(int size) {
            return new BillResponse[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }
}
