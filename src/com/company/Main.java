package com.company;
import java.util.Random;

public class Main {


    public static void main(String[] args) {
        int bit = 10;
        int population = 10 * bit;
        int gens = 100;
        double F = 0.2;
        double Cr = 0.2;
        int x_max = 100;
        int x_min = -100;
        int goalCounter;
        int counterGlobalWhile;
        int best_result = 0;
        double goal = Math.pow(10, -8);
        double[][] startArray = new double[population][bit];
        double finalSred;
        double countSred;

        int success1 = 0; // Кол-во успешных применений оператора мутации_1
        int used1 = 0;
        int success2 = 0; // Кол-во успешных применений оператора мутации_2
        int used2 = 0;
        int success3 = 0; // Кол-во успешных применений оператора мутации_3
        int used3 = 0;
        double Pbuff = 0;

        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;

        double[] BestHistory = new double[gens+1];
        double[] MutUpgrade1 = new double[population+1];
        double[] MutHistory1 = new double[gens];
        double AvgMut1 = 0;
        double SumAvgMut1 = 0;
        double[] MutUpgrade2 = new double[population+1];
        double[] MutHistory2 = new double[gens];
        double AvgMut2 = 0;
        double SumAvgMut2 = 0;
        double[] MutUpgrade3 = new double[population+1];
        double[] MutHistory3 = new double[gens];
        double AvgMut3 = 0;
        double SumAvgMut3 = 0;

        double Pall = 0.2 / 3;
        double[] Ri = new double[population];

        double P1 = 0.33;
        double P2 = 0.33;
        double P3 = 1 - (P1+P2);

        int number_mutation;

        Random random = new Random();
        Functions functions = new Functions();
        Mutations mutations = new Mutations();

        for (int number_function = 1; number_function < 12; number_function++)
        {
            goalCounter = 0;
            counterGlobalWhile = 0;
            finalSred = 0;
            countSred = 0;
            P1 = 0.33;
            P2 = 0.33;
            P3 = 1 - (P1+P2);
            used1 = 0;
            used2 = 0;
            used3 = 0;
            counter1 = 0;
            counter2 = 0;
            counter3 = 0;
            while (counterGlobalWhile < 50)
            {
                for (int i = 0; i < population; i++)
                {
                    for (int j = 0; j < bit; j++)
                    {
                        startArray[i][j] = x_min + random.nextDouble() * (x_max - x_min);
                    }
                }//Заполняем массив начальными данными


                for (int x = 0; x < gens; x++)
                {
                    double[] result = new double[population];
                    for (int i = 0; i < population; i++)
                    {
                        switch (number_function)
                        {
                            case 1:
                                result[i] = functions.function_one(bit, startArray, i);
                                break;
                            case 2:
                                result[i] = functions.function_two(bit, startArray, i);
                                break;
                            case 3:
                                result[i] = functions.function_three(bit, startArray, i);
                                break;
                            case 4:
                                result[i] = functions.function_four(bit, startArray, i);
                                break;
                            case 5:
                                result[i] = functions.function_five(bit, startArray, i, population);
                                break;
                            case 6:
                                result[i] = functions.function_six(bit, startArray, i , i-1);
                                break;
                            case 7:
                                result[i] = functions.function_seven(bit, startArray, i);
                                break;
                            case 8:
                                result[i] = functions.function_eight(bit, startArray, i);
                                break;
                            case 9:
                                result[i] = functions.function_nine(bit, startArray, i);
                                break;
                            case 10:
                                result[i] = functions.function_ten(bit, startArray, i, i+1, population);
                                break;
                            case 11:
                                result[i] = functions.function_eleven(bit, startArray, i);
                                break;
                            default:
                                break;
                        }
                    }//Значение функции

                    double[][] mutationArray = new double[population][bit];

                    double randMutation = random.nextDouble();
                    if (randMutation < P1){number_mutation = 1; used1++;}
                    else if ((randMutation < (P1+P2)) &&(randMutation >= P1) ){number_mutation = 2; used2++;}
                    else {number_mutation = 3; used3++;}

                    switch (number_mutation)
                    {
                        case 1:
                            mutationArray = mutations.different(population, bit, startArray, F, mutationArray);
                            break;
                        case 2:
                            mutationArray = mutations.targetToBest(population, bit, startArray, F, mutationArray, best_result);
                            break;
                        case 3:
                            mutationArray = mutations.rand2(population, bit, startArray, F, mutationArray);
                            break;
                        default:
                            break;
                    }//Мутируем

                    double rand = 0;
                    double j_rand = 0;
                    for (int i = 0; i < population; i++)
                    {
                        for (int j = 0; j < bit; j++)
                        {
                            rand = random.nextDouble();
                            j_rand = random.nextInt(bit)+1;
                            if (rand <= Cr || j == j_rand)
                            {
                                mutationArray[i][j] = mutationArray[i][j];
                            }
                            else
                            {
                                mutationArray[i][j] = startArray[i][j];
                            }
                        }
                    }//Скрещиваем

                    for (int i = 0; i < population; i++)
                    {
                        for (int j = 0; j < bit; j++)
                        {
                            if (mutationArray[i][j] > x_max)
                            {
                                mutationArray[i][j] = (startArray[i][j] + x_max) / 2;
                            }
                            if (mutationArray[i][j] < x_min)
                            {
                                mutationArray[i][j] = (startArray[i][j] + x_min) / 2;
                            }
                        }
                    }//Загоняем значения в границы

                    double[] result2 = new double[population];

                    for (int i = 0; i < population; i++)
                    {
                        switch (number_function)
                        {
                            case 1:
                                result2[i] = functions.function_one(bit, mutationArray, i);
                                break;
                            case 2:
                                result2[i] = functions.function_two(bit, mutationArray, i);
                                break;
                            case 3:
                                result2[i] = functions.function_three(bit, mutationArray, i);
                                break;
                            case 4:
                                result2[i] = functions.function_four(bit, mutationArray, i);
                                break;
                            case 5:
                                result2[i] = functions.function_five(bit, mutationArray, i, population);
                                break;
                            case 6:
                                result2[i] = functions.function_six(bit, mutationArray, i , i-1);
                                break;
                            case 7:
                                result2[i] = functions.function_seven(bit, mutationArray, i);
                                break;
                            case 8:
                                result2[i] = functions.function_eight(bit, mutationArray, i);
                                break;
                            case 9:
                                result2[i] = functions.function_nine(bit, mutationArray, i);
                                break;
                            case 10:
                                result2[i] = functions.function_ten(bit, mutationArray, i, i+1, population);
                                break;
                            case 11:
                                result2[i] = functions.function_eleven(bit, mutationArray, i);
                                break;
                            default:
                                break;
                        }
                    }//Значение функции

                    counter1 = 0;
                    counter2 = 0;
                    counter3 = 0;

                    for (int i = 0; i < population; i++)
                    {
                        if (result[i] >= result2[i])
                        {
                            for (int j = 0; j < bit; j++)
                            {
                                startArray[i][j] = mutationArray[i][j];
                            }
                            switch (number_mutation){
                                case 1:
                                    MutUpgrade1[counter1] = result[i] - result2[i];
                                    counter1++;
                                    break;
                                case 2:
                                    MutUpgrade2[counter2] = result[i] - result2[i];
                                    counter2++;
                                    break;
                                case 3:
                                    MutUpgrade3[counter3] = result[i] - result2[i];
                                    counter3++;
                                    break;
                                default:
                                    break;
                            }
                            result[i] = result2[i];
                        }
                    }//Селекционируем

                    for (int i = 0; i < population; i++)
                    {
                        if (result[best_result] > result[i])
                        {
                            best_result = i;
                        }
                    }//Ищем лучший результат

                    BestHistory[x] = result[best_result];


                    for(int i = 0; i<population; i++){
                        SumAvgMut1 += MutUpgrade1[i];
                    }
                    for(int i = 0; i<population; i++){
                        SumAvgMut2 += MutUpgrade2[i];
                    }
                    for(int i = 0; i<population; i++){
                        SumAvgMut3 += MutUpgrade3[i];
                    }
                    AvgMut1 = SumAvgMut1/used1;
                    AvgMut2 = SumAvgMut2/used2;
                    AvgMut3 = SumAvgMut3/used3;
                    // Не забыть чтобы сумма P=1
                    if(AvgMut1>AvgMut2 && AvgMut1>AvgMut3){P1 += 0.02; P2 -= 0.01; P3 -= 0.01;
                        if(P1>=0.78){Pbuff = P1; P1 = 0.8; P2-=(P1-Pbuff)/2;  P3-=(P1-Pbuff)/2; }
                        if(P2<=0.1){P2 = 0.1;}
                        if (P3<=0.1){P3 = 0.1;}}

                    if(AvgMut2>AvgMut1 && AvgMut2>AvgMut3){P1 -= 0.01; P2 += 0.02; P3 -= 0.01;
                        if(P2>=0.78){Pbuff = P2; P2 = 0.8; P1-=(P2-Pbuff)/2;  P3-=(P2-Pbuff)/2;}
                        if(P1<=0.1){P1 = 0.1;}
                        if (P3<=0.1){P3 = 0.1;}}

                    if(AvgMut3>AvgMut1 && AvgMut3>AvgMut2){P1 -= 0.01; P2 -= 0.01; P3 += 0.02;
                        if(P3>=0.78){Pbuff = P3; P3 = 0.8; P1-=(P3-Pbuff)/2;  P2-=(P3-Pbuff)/2;}
                        if(P1<=0.1){P1 = 0.1;}
                        if (P2<=0.1){P2 = 0.1;}}

                    if (result[best_result] <= goal)
                    {
                        goalCounter++;
                        break;
                    }
                    countSred++;
                    finalSred += result[best_result];
                }
                counterGlobalWhile++;
            }
            System.out.println(goalCounter);
            System.out.println("Среднее значение функции " + number_function + " = " + (finalSred/countSred)); // + Среднее значение
        }
    }
}
