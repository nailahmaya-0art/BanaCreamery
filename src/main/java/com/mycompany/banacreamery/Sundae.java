package com.mycompany.banacreamery;

public class Sundae extends ProdukEsKrim implements DapatDinilai {
    private String jenisTopping;
    private int jumlahScoop;

    public Sundae(String kodeProduk, String namaVarian, double hargaDasar, int stok, 
                  String jenisTopping, int jumlahScoop) {
        super(kodeProduk, namaVarian, hargaDasar, stok);
        this.jenisTopping = jenisTopping;
        this.jumlahScoop = jumlahScoop;
    }

    public String getJenisTopping() { return jenisTopping; }
    public int getJumlahScoop() { return jumlahScoop; }

    @Override
    public double hitungHargaJual() {
        return getHargaDasar() + ((jumlahScoop > 1 ? jumlahScoop - 1 : 0) * 3000);
    }

    @Override
    public void tampilkanInfo() {
        String spec = String.format("Topping: %s (%d Scoop)", jenisTopping, jumlahScoop);
        System.out.printf("| %-6s | %-18s | Rp%-9.2f | %-4d | %-14s | %-38s |%n",
                getKodeProduk(), getNamaVarian(), hitungHargaJual(), getStok(), 
                "Sundae", spec);
    }

    @Override
    public void caraPenyajian() {
        System.out.println("-> Penyajian Sundae: Disajikan dalam gelas tinggi dengan limpahan topping " + jenisTopping + ".");
    }

    @Override
    public void hitungDiskonKadaluarsa() {
        System.out.println("   [Aturan Diskon] Potongan Rp 5.000 untuk pembelian jam terakhir operasional toko.");
    }

    @Override
    public void beriRating(int bintang) {
        System.out.println("   [Ulasan Pelanggan] Ulasan Sundae dicatat dengan rating " + bintang + "/5 bintang.");
    }
}