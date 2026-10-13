package com.mycompany.banacreamery;

public class EsKrimPop extends ProdukEsKrim implements DapatDipesan {
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
        String spec = String.format("Stik: %s (Dip: %s)", jenisStik, (adaLapisanCokelat ? "Ya" : "Tidak"));
        System.out.printf("| %-6s | %-18s | Rp%-9.2f | %-4d | %-14s | %-38s |%n",
                getKodeProduk(), getNamaVarian(), hitungHargaJual(), getStok(), 
                "Es Krim Pop", spec);
    }

    @Override
    public void caraPenyajian() {
        System.out.println("-> Penyajian Es Krim Pop: Disajikan beku padat bersama stik " + jenisStik + ".");
    }

    @Override
    public void hitungDiskonKadaluarsa() {
        System.out.println("   [Aturan Diskon] Buy 1 Get 1 jika mendekati tanggal kadaluarsa.");
    }

    @Override
    public void prosesPemesananKhusus() {
        System.out.println("   [PESAN KHUSUS] Es Krim Pop dikirim dengan dry-ice kemasan cetak khusus.");
    }
}