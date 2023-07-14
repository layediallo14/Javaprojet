public class GestionnaireTache {
    private Tache[] taches;
    private int nombreTache;

    public GestionnaireTache(int nombreMax) {
        this.taches = new Tache[nombreMax];
        this.nombreTache = 0;
    }

    public boolean ajouter(Tache tache) {
        if (nombreTache < taches.length) {
            taches[nombreTache] = tache;
            nombreTache++;
            return true;
        }
        return false;
    }

    public boolean modifier(Tache tache) {
        int id = tache.getIdentifiant();
        for (int i = 0; i < nombreTache; i++) {
            if (taches[i].getIdentifiant() == id) {
                taches[i] = tache;
                return true;
            }
        }
        return false;
    }

    public boolean supprimer(int id) {
        for (int i = 0; i < nombreTache; i++) {
            if (taches[i].getIdentifiant() == id) {
                for (int j = i; j < nombreTache - 1; j++) {
                    taches[j] = taches[j + 1];
                }
                taches[nombreTache - 1] = null;
                nombreTache--;
                return true;
            }
        }
        return false;
    }

    public Tache[] lister() {
        Tache[] listeTaches = new Tache[nombreTache];
        System.arraycopy(taches, 0, listeTaches, 0, nombreTache);
        return listeTaches;
    }

    public Tache[] lister(String etat) {
        int count = 0;
        for (int i = 0; i < nombreTache; i++) {
            if (taches[i].getEtat().equals(etat)) {
                count++;
            }
        }
        Tache[] listeTaches = new Tache[count];
        int index = 0;
        for (int i = 0; i < nombreTache; i++) {
            if (taches[i].getEtat().equals(etat)) {
                listeTaches[index] = taches[i];
                index++;
            }
        }
        return listeTaches;
    }
}
