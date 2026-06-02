package model;

public class Barang {

    private int id;
    private String namaBarang;
    private int harga;
    private int stok;

    public Barang() {
    }

    public Barang(int id, String namaBarang, int harga, int stok) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}