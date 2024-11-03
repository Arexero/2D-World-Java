package controleur;
import java.util.ArrayList;

 public abstract class AbstractEcoutableCarte implements ModelEcoutable{
  protected ArrayList<ModelMiseAJour> listEcouteur;

  public AbstractEcoutableCarte(){
    this.listEcouteur = new ArrayList<ModelMiseAJour> ();
  }

  public void ajoutEcouteur(ModelMiseAJour e){
    this.listEcouteur.add(e);
  }

  public void retraitEcouteur(ModelMiseAJour e){
    this.listEcouteur.remove(e);
  }

  public void firechange(){
	//System.out.println(this.listEcouteur);
    for(int i = 0;i<this.listEcouteur.size();i++){
      this.listEcouteur.get(i).modelMiseAJour(this);
    }
  }
}
