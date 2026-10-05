package com.mycompany.banacreamery;

public class ProdukEsKrim {
    private String kodeProduk;
    private String namaVarian;
    private double hargaDasar;
    private int stok;

    public static int totalProdukBerhasilDibuat = 0;

    public ProdukEsKrim(String kodeProduk, String namaVarian, double hargaDasar, int stok) {
        this.kodeProduk = kodeProduk;
        this.namaVarian = namaVarian;
        setHargaDasar(hargaDasar);
        setStok(stok);
        totalProdukBerhasilDibuat++;
    }

    public String getKodeProduk() { return kodeProduk; }
    public String getNamaVarian() { return namaVarian; }
    public double getHargaDasar() { return hargaDasar; }
    public int getStok() { return stok; }

    public void setHargaDasar(double hargaDasar) {
        this.hargaDasar = (hargaDasar < 0) ? 0 : hargaDasar;
    }

    public void setStok(int stok) {
        this.stok = (stok < 0) ? 0 : stok;
    }

    public double hitungHargaJual() {
        return hargaDasar;
    }

    public void tampilkanInfo() {
        System.out.printf("[%s] %-18s | Harga Dasar: Rp%-8.0f | Stok: %-3d ", 
                kodeProduk, namaVarian, hargaDasar, stok);
    }

    public void caraPenyajian() {
        System.out.println("-> Cara Penyajian: Disajikan dalam wadah standar.");
    }
}