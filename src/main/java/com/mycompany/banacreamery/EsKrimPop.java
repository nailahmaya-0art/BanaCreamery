package com.mycompany.banacreamery;

public class EsKrimPop extends ProdukEsKrim {
    private String jenisStik;
    private boolean adaLapisanCokelat;

    public EsKrimPop(String kodeProduk, String namaVarian, double hargaDasar, int stok, 
                     String jenisStik, boolean adaLapisanCokelat) {
        super(kodeProduk, namaVarian, hargaDasar, stok);
        this.jenisStik = jenisStik;
        this.adaLapisanCokelat = adaLapisanCokelat;
    }

    @Override
    public double hitungHargaJual() {
        return getHargaDasar() + (adaLapisanCokelat ? 2500 : 0);
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("| %-7s | %-20s | Rp%-10.2f | %-4d | %-15s | Stik: %-7s (Dip: %-3s) |%n",
                getKodeProduk(), getNamaVarian(), hitungHargaJual(), getStok(), 
                "Es Krim Pop", jenisStik, (adaLapisanCokelat ? "Ya" : "Tidak"));
    }

    @Override
    public void caraPenyajian() {
        System.out.println("-> Penyajian Es Krim Pop: Disajikan beku padat bersama stik " + jenisStik + ".");
    }
}