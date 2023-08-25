package com.company;
import java.util.Random;

public class Mutations {
    public double[][] different(int population, int bit, double[][] startArray, double F, double[][] mutationArray)
    {
        Random random = new Random();
        int firstIndiv;
        int secondIndiv;
        int thirdIndiv;
        firstIndiv = random.nextInt(population);
        secondIndiv = random.nextInt(population);
        thirdIndiv = random.nextInt(population);
        for (int i = 0; i < population; i++)
        {
            while(firstIndiv == secondIndiv || firstIndiv == thirdIndiv || secondIndiv == thirdIndiv || firstIndiv == i ||
                    secondIndiv == i || thirdIndiv == i){
                firstIndiv = random.nextInt(population);
                secondIndiv = random.nextInt(population);
                thirdIndiv = random.nextInt(population);}
            for (int j = 0; j < bit; j++)
            {
                mutationArray[i][j] = startArray[firstIndiv][j] + F * (startArray[secondIndiv][j] - startArray[thirdIndiv][j]);
            }
        }
        return mutationArray;
    }
    public double[][] targetToBest(int population, int bit, double[][] startArray, double F, double[][] mutationArray, int
            best_result)
    {
        Random random = new Random();
        int firstIndiv;
        int secondIndiv;
        for (int i = 0; i < population; i++)
        {
            firstIndiv = random.nextInt(population);
            secondIndiv = random.nextInt(population);

            for (int j = 0; j < bit; j++)
            {
                mutationArray[i][j] = startArray[i][j] + F * (startArray[best_result][j] - startArray[i][j]) + F * F *
                        (startArray[firstIndiv][j] - startArray[secondIndiv][j]);
            }
        }
        return mutationArray;

    }
    public double[][] rand2(int population, int bit, double[][] startArray, double F, double[][] mutationArray)
    {
        Random random = new Random();
        int firstIndiv;
        int secondIndiv;
        int thirdIndiv;
        int fourthIndiv;
        int fifthIndiv;

        for (int i = 0; i < population; i++)
        {
            firstIndiv = random.nextInt(population);
            secondIndiv = random.nextInt(population);
            thirdIndiv = random.nextInt(population);
            fourthIndiv = random.nextInt(population);
            fifthIndiv = random.nextInt(population);

            for (int j = 0; j < bit; j++)
            {
                mutationArray[i][j] = startArray[firstIndiv][j] + F * (startArray[secondIndiv][j] - startArray[thirdIndiv][j]) + F
                        * (startArray[fourthIndiv][j] - startArray[fifthIndiv][j]);
            }
        }
        return mutationArray;
    }
}
