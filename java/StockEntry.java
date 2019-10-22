import java.util.Date;

public class StockEntry {
    Date date;
    Float value;

    @Override
    public String toString() {
        return "StockEntry{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
