
package resumabledownload2.indocyber.com.resumabledownload2.model.Identity;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import resumabledownload2.indocyber.com.resumabledownload2.DemoApplication;

@Table(database = DemoApplication.class)
public class Identity extends BaseModel implements Serializable, Parcelable
{

    @SerializedName("nama4kolom")
    @Expose
    @Column
    private String nama4kolom;
    @SerializedName("kolomtwelve")
    @Expose
    @Column
    private String kolomtwelve;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private String id;
    @SerializedName("nama3")
    @Expose
    @Column
    private String nama3;
    @SerializedName("citacita4kolom")
    @Expose
    @Column
    private String citacita4kolom;
    @SerializedName("nama")
    @Expose
    @Column
    private String nama;
    @SerializedName("Pekerjaan4kolom")
    @Expose
    @Column
    private String pekerjaan4kolom;
    @SerializedName("kolomsebelas")
    @Expose
    @Column
    private String kolomsebelas;
    @SerializedName("Pekerjaan3")
    @Expose
    @Column
    private String pekerjaan3;
    @SerializedName("Pekerjaan")
    @Expose
    @Column
    private String pekerjaan;
    @SerializedName("citacita2kerjaan")
    @Expose
    @Column
    private String citacita2kerjaan;
    @SerializedName("citacita3")
    @Expose
    @Column
    private String citacita3;
    public final static Creator<Identity> CREATOR = new Creator<Identity>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Identity createFromParcel(Parcel in) {
            return new Identity(in);
        }

        public Identity[] newArray(int size) {
            return (new Identity[size]);
        }

    }
    ;
    private final static long serialVersionUID = -5189455444809734589L;

    protected Identity(Parcel in) {
        this.nama4kolom = ((String) in.readValue((String.class.getClassLoader())));
        this.kolomtwelve = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.nama3 = ((String) in.readValue((String.class.getClassLoader())));
        this.citacita4kolom = ((String) in.readValue((String.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.pekerjaan4kolom = ((String) in.readValue((String.class.getClassLoader())));
        this.kolomsebelas = ((String) in.readValue((String.class.getClassLoader())));
        this.pekerjaan3 = ((String) in.readValue((String.class.getClassLoader())));
        this.pekerjaan = ((String) in.readValue((String.class.getClassLoader())));
        this.citacita2kerjaan = ((String) in.readValue((String.class.getClassLoader())));
        this.citacita3 = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Identity() {
    }

    /**
     * 
     * @param pekerjaan4kolom
     * @param citacita4kolom
     * @param id
     * @param pekerjaan3
     * @param nama4kolom
     * @param citacita2kerjaan
     * @param kolomsebelas
     * @param nama
     * @param kolomtwelve
     * @param pekerjaan
     * @param nama3
     * @param citacita3
     */
    public Identity(String nama4kolom, String kolomtwelve, String id, String nama3, String citacita4kolom, String nama, String pekerjaan4kolom, String kolomsebelas, String pekerjaan3, String pekerjaan, String citacita2kerjaan, String citacita3) {
        super();
        this.nama4kolom = nama4kolom;
        this.kolomtwelve = kolomtwelve;
        this.id = id;
        this.nama3 = nama3;
        this.citacita4kolom = citacita4kolom;
        this.nama = nama;
        this.pekerjaan4kolom = pekerjaan4kolom;
        this.kolomsebelas = kolomsebelas;
        this.pekerjaan3 = pekerjaan3;
        this.pekerjaan = pekerjaan;
        this.citacita2kerjaan = citacita2kerjaan;
        this.citacita3 = citacita3;
    }

    public String getNama4kolom() {
        return nama4kolom;
    }

    public void setNama4kolom(String nama4kolom) {
        this.nama4kolom = nama4kolom;
    }

    public Identity withNama4kolom(String nama4kolom) {
        this.nama4kolom = nama4kolom;
        return this;
    }

    public String getKolomtwelve() {
        return kolomtwelve;
    }

    public void setKolomtwelve(String kolomtwelve) {
        this.kolomtwelve = kolomtwelve;
    }

    public Identity withKolomtwelve(String kolomtwelve) {
        this.kolomtwelve = kolomtwelve;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Identity withId(String id) {
        this.id = id;
        return this;
    }

    public String getNama3() {
        return nama3;
    }

    public void setNama3(String nama3) {
        this.nama3 = nama3;
    }

    public Identity withNama3(String nama3) {
        this.nama3 = nama3;
        return this;
    }

    public String getCitacita4kolom() {
        return citacita4kolom;
    }

    public void setCitacita4kolom(String citacita4kolom) {
        this.citacita4kolom = citacita4kolom;
    }

    public Identity withCitacita4kolom(String citacita4kolom) {
        this.citacita4kolom = citacita4kolom;
        return this;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Identity withNama(String nama) {
        this.nama = nama;
        return this;
    }

    public String getPekerjaan4kolom() {
        return pekerjaan4kolom;
    }

    public void setPekerjaan4kolom(String pekerjaan4kolom) {
        this.pekerjaan4kolom = pekerjaan4kolom;
    }

    public Identity withPekerjaan4kolom(String pekerjaan4kolom) {
        this.pekerjaan4kolom = pekerjaan4kolom;
        return this;
    }

    public String getKolomsebelas() {
        return kolomsebelas;
    }

    public void setKolomsebelas(String kolomsebelas) {
        this.kolomsebelas = kolomsebelas;
    }

    public Identity withKolomsebelas(String kolomsebelas) {
        this.kolomsebelas = kolomsebelas;
        return this;
    }

    public String getPekerjaan3() {
        return pekerjaan3;
    }

    public void setPekerjaan3(String pekerjaan3) {
        this.pekerjaan3 = pekerjaan3;
    }

    public Identity withPekerjaan3(String pekerjaan3) {
        this.pekerjaan3 = pekerjaan3;
        return this;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public Identity withPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
        return this;
    }

    public String getCitacita2kerjaan() {
        return citacita2kerjaan;
    }

    public void setCitacita2kerjaan(String citacita2kerjaan) {
        this.citacita2kerjaan = citacita2kerjaan;
    }

    public Identity withCitacita2kerjaan(String citacita2kerjaan) {
        this.citacita2kerjaan = citacita2kerjaan;
        return this;
    }

    public String getCitacita3() {
        return citacita3;
    }

    public void setCitacita3(String citacita3) {
        this.citacita3 = citacita3;
    }

    public Identity withCitacita3(String citacita3) {
        this.citacita3 = citacita3;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(nama4kolom);
        dest.writeValue(kolomtwelve);
        dest.writeValue(id);
        dest.writeValue(nama3);
        dest.writeValue(citacita4kolom);
        dest.writeValue(nama);
        dest.writeValue(pekerjaan4kolom);
        dest.writeValue(kolomsebelas);
        dest.writeValue(pekerjaan3);
        dest.writeValue(pekerjaan);
        dest.writeValue(citacita2kerjaan);
        dest.writeValue(citacita3);
    }

    public int describeContents() {
        return  0;
    }

}
