package pl.dskrzyniarz;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("a:");
        int a = scanner.nextInt();
        System.out.print("b:");
        int b = scanner.nextInt();
        System.out.print("c:");
        int c = scanner.nextInt();
        System.out.print("d:");
        int d = scanner.nextInt();
        System.out.print("Pk:");
        double pk = scanner.nextDouble();
        System.out.print("Pm:");
        double pm = scanner.nextDouble();



        CubicFunction function = new CubicFunction(a,b,c,d);
        final int CHROMOSOME_SIZE = 5;
        final int GENERATION_SIZE = 5;
        List<Chromosome> chromosomes = new ArrayList<>();

        for(int i=0; i<GENERATION_SIZE; i++){
            chromosomes.add(new Chromosome(function, CHROMOSOME_SIZE));
        }

        Collections.sort(chromosomes.reversed());
        System.out.println("Initial set of chromosomes:");
        for(Chromosome chromosome: chromosomes){
            System.out.println(chromosome);
        }
        System.out.println("------------");
        Random random = new Random();
        for(int i=0, j=0; i<100 && j<10; i++){
            List<Chromosome> nextGen = new ArrayList<>();
            nextGen.add(chromosomes.getFirst());
            long fitnessSum = 0;
            for(Chromosome chromosome: chromosomes){
                fitnessSum += chromosome.getFitness();
            }
            while(nextGen.size() < GENERATION_SIZE){
                Chromosome firstParent = new Chromosome(function);
                Chromosome secondParent = new Chromosome(function);
                Chromosome child;

                long randomValue = random.nextLong(fitnessSum);
                long counter = 0;
                for(Chromosome chromosome: chromosomes){
                    counter += chromosome.getFitness();
                    if(counter>= randomValue){
                        firstParent = chromosome;
                        break;
                    }
                }
                if(random.nextDouble(1.0)<=pm) {
                    counter = 0;
                    randomValue = random.nextLong(fitnessSum);
                    for (Chromosome chromosome : chromosomes) {
                        counter += chromosome.getFitness();
                        if (counter >= randomValue) {
                            secondParent = chromosome;
                            break;
                        }
                    }
                    child = new Chromosome(firstParent, secondParent);

                } else{
                    child = new Chromosome(firstParent);
                }
                if(random.nextDouble(1.0)<=pk){
                    child.mutate();
                }
                nextGen.add(child);
            }
            nextGen.sort(Collections.reverseOrder());
            if(chromosomes.getFirst().equals(nextGen.getFirst())){
                j++;
            }
            else{
                j=0;
            }
            chromosomes = nextGen;
            System.out.println("Generation " + i + ": " + chromosomes.getFirst());
        }

    }
}