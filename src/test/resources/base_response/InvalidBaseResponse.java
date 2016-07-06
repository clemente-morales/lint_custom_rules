public class InvalidBaseResponse extends BaseResponse {
    private String title;
    private String screenHeading;
    private String imageURL;

    public InvalidBaseResponse(String pageType, String header, String presentationStyle) {
        super(pageType, header, presentationStyle);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScreenHeading() {
        return screenHeading;
    }

    public void setScreenHeading(String screenHeading) {
        this.screenHeading = screenHeading;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
