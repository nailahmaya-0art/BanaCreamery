package com.mycompany.banacreamery;

public class Sundae extends ProdukEsKrim {
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
        System.out.printf("| %-7s | %-20s | Rp%-10.2f | %-4d | %-15s | Topping: %-10s (%d Scoop) |%n",
                getKodeProduk(), getNamaVarian(), hitungHargaJual(), getStok(), 
                "Sundae", jenisTopping, jumlahScoop);
    }

    @Override
    public void caraPenyajian() {
        System.out.println("-> Penyajian Sundae: Disajikan dalam gelas tinggi dengan limpahan topping " + jenisTopping + ".");
    }
}