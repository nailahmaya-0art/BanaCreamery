package com.mycompany.banacreamery;

public class Gelato extends ProdukEsKrim implements DapatDipesan, DapatDinilai {
    private double persentaseLemakSusu;
    private String rasaBase;

    public Gelato(String kodeProduk, String namaVarian, double hargaDasar, int stok, 
                  double persentaseLemakSusu, String rasaBase) {
        super(kodeProduk, namaVarian, hargaDasar, stok);
        this.persentaseLemakSusu = persentaseLemakSusu;
        this.rasaBase = rasaBase;
    }

    @Override
    public double hitungHargaJual() {
        return getHargaDasar() * 1.15;
    }

    @Override
    public void tampilkanInfo() {
        String spec = String.format("Base: %s (Lemak: %.1f%%)", rasaBase, persentaseLemakSusu);
        System.out.printf("| %-6s | %-18s | Rp%-9.2f | %-4d | %-14s | %-38s |%n",
                getKodeProduk(), getNamaVarian(), hitungHargaJual(), getStok(), 
                "Gelato Artisan", spec);
    }

    @Override
    public void caraPenyajian() {
        System.out.println("-> Penyajian Gelato: Di-scoop padat dan disajikan kaku pada suhu -12 derajat Celcius.");
    }

    @Override
    public void hitungDiskonKadaluarsa() {
        System.out.println("   [Aturan Diskon] Diskon 20% jika sisa masa simpan kurang dari 3 hari.");
    }

    @Override
    public void prosesPemesananKhusus() {
        System.out.println("   [PESAN KHUSUS] Pemesanan Gelato membutuhkan waktu pembekuan 1x24 jam.");
    }

    @Override
    public void beriRating(int bintang) {
        System.out.println("   [Ulasan Pelanggan] Ulasan Gelato dicatat dengan rating " + bintang + "/5 bintang.");
    }
}