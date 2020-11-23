package pl.sda.library.domain.model;


public class BookViewOption {
    private ViewType viewType = ViewType.SMALL;

    private static BookViewOption instance = new BookViewOption();

    private BookViewOption() {
        this.viewType = ViewType.SMALL;
    }


    public static BookViewOption getInstance(){
        int i =0;
        return instance;
    }
    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }
    public void setBookViewString(String view) {
        int i =0;
        this.viewType = ViewType.valueOf(view);
    }
}
