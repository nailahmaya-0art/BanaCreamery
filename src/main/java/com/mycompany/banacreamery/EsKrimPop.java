package com.mycompany.banacreamery;

public class EsKrimPop extends ProdukEsKrim {
    private String jenisStik;
    private boolean adaCoatingCokelat;

    public EsKrimPop(String kodeProduk, String namaVarian, double hargaDasar, int stok, String jenisStik, boolean adaCoatingCokelat) {
        super(kodeProduk, namaVarian, hargaDasar, stok);
        this.jenisStik = jenisStik;
        this.adaCoatingCokelat = adaCoatingCokelat;
    }

    public String getJenisStik() {
        return jenisStik;
    }

    public void setJenisStik(String jenisStik) {
        this.jenisStik = jenisStik;
    }

    public boolean isAdaCoatingCokelat() {
        return adaCoatingCokelat;
    }

    public void setAdaCoatingCokelat(boolean adaCoatingCokelat) {
        this.adaCoatingCokelat = adaCoatingCokelat;
    }

    @Override
    public double hitungHargaJual() {
        double hargaFinal = super.getHargaDasar();
        if (adaCoatingCokelat) {
            hargaFinal += 3000;
        }
        return hargaFinal;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        String coatingStr = adaCoatingCokelat ? "Coating Cokelat" : "Tanpa Coating";
        System.out.printf("| Es Krim Popsicle| Stik: %-10s (%s)%n", 
                this.jenisStik, coatingStr);
    }
}