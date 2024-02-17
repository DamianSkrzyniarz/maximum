package pl.dskrzyniarz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome>{

    private Polynomial polynomial;
    private List<Boolean> genes;
    private int x;

    public Chromosome(Polynomial polynomial) {
        this.polynomial = polynomial;
        int size = 5;
        Random random = new Random();
        genes = new ArrayList<>();
        while(genes.size()<size){
            genes.add(random.nextBoolean());
        }
        x= calculateX();
    }

    public Chromosome(Polynomial polynomial, int size) {
        this.polynomial = polynomial;
        Random random = new Random();
        this.genes = new ArrayList<>();
        while(genes.size()<size){
            genes.add(random.nextBoolean());
        }
        x= calculateX();
    }
    public Chromosome(Chromosome singleParent){
        this.polynomial = singleParent.polynomial;
        this.genes = new ArrayList<>(singleParent.genes);
        this.x = calculateX();
    }
    public Chromosome(Chromosome firstParent, Chromosome secondParent) {
        this.polynomial = firstParent.polynomial;
        Random random = new Random();
        genes = new ArrayList<>();
        int size = firstParent.getGenes().size();
        int split = random.nextInt(size);
        while(genes.size()<split){
            genes.add(firstParent.getGenes().get(genes.size()));
        }
        while(genes.size()<size){
            genes.add(secondParent.getGenes().get(genes.size()));
        }
        x= calculateX();
    }

    private int calculateX(){
        x = 0;
        for(int i=0; i<genes.size(); i++){
            if(genes.get(i)){
                x+= (int) Math.pow(2, i);
            }
        }
        return x;
    }

    public long getFitness(){
        return (polynomial.calculate(x));
    }

    public List<Boolean> getGenes() {
        return genes;
    }

    public void mutate(){
        Random random = new Random();
        int mutatingGene = random.nextInt(genes.size());
        if(genes.get(mutatingGene)){
            genes.set(mutatingGene, false);
        }
        else {
            genes.set(mutatingGene, true);
        }
        x=calculateX();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
            builder.append("(");
            for(Boolean gene: genes){
                if(gene){
                    builder.append("1");
                }
                else builder.append("0");
            }
            builder.append(") x=").append(x);
            builder.append(" f(x)=").append(getFitness());

        return builder.toString();
    }

    @Override
    public int compareTo(Chromosome o) {
        return Long.compare(this.getFitness(), o.getFitness());
    }
}
