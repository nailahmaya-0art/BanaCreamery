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

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaVarian() {
        return namaVarian;
    }

    public void setNamaVarian(String namaVarian) {
        this.namaVarian = namaVarian;
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    public void setHargaDasar(double hargaDasar) {
        if (hargaDasar < 0) {
            this.hargaDasar = 0;
        } else {
            this.hargaDasar = hargaDasar;
        }
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        if (stok < 0) {
            this.stok = 0;
        } else {
            this.stok = stok;
        }
    }

    public double hitungHargaJual() {
        return this.hargaDasar;
    }

    public void tampilkanInfo() {
        System.out.printf("| %-7s | %-20s | Rp%-11.2f | %-4d ", 
                this.kodeProduk, this.namaVarian, hitungHargaJual(), this.stok);
    }
}