package edu.umb.cs681.hw03;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PviCalculate{
    public static void main(String[] args) throws Exception {

        var path = Paths.get("src/edu/umb/cs681/hw03/pvi_data.csv");

        var neighbouringCounties = Files.readAllLines(path)
                .stream()
                .filter(line ->
                        line.contains("Massachusetts, Plymouth")
                                || line.contains("Massachusetts, Norfolk")
                                || line.contains("Massachusetts, Barnstable")
                                || line.contains("Massachusetts, Bristol")
                )
                .map(line -> line.split(","))
                .collect(Collectors.toList());

        var averagePvi = neighbouringCounties.stream()
                .map(line -> Double.parseDouble(line[0].replace("\"", "")))
                .reduce(new double[2], (result, pvi) -> {
                            var av = (result[1] * result[0] + pvi) / ++result[0];
                            return new double[]{result[0]++, av};
                        },
                        (finalResult, intermediateResult) -> finalResult
                )[1];
        System.out.print("Plymouth's average PVI of neighbour is: ");
        System.out.println(averagePvi);

        var averageInfectionRate = neighbouringCounties.stream()
                .map(line -> Double.parseDouble(line[7].replace("\"", "")))
                .reduce(new double[2], (result, pvi) -> {
                            var av = (result[1] * result[0] + pvi) / ++result[0];
                            return new double[]{result[0]++, av};
                        },
                        (finalResult, intermediateResult) -> finalResult
                )[1];
        System.out.print("Plymouth's average infection rate of neighbour is: ");
        System.out.println(averageInfectionRate);

        var averageVaxRate = neighbouringCounties.stream()
                .map(line -> Double.parseDouble(line[11].replace("\"", "")))
                .reduce(new double[2], (result, pvi) -> {
                            var av = (result[1] * result[0] + pvi) / ++result[0];
                            return new double[]{result[0]++, av};
                        },
                        (finalResult, intermediateResult) -> finalResult
                )[1];
        System.out.print("Plymouth's average vaccination rate ofneighbour is: ");
        System.out.println(averageVaxRate);

        var massCounties = Files.readAllLines(path)
                .stream()
                .filter(line -> line.contains("Massachusetts"))
                .map(line -> line.split(","))
                .collect(Collectors.toList());

        var meanPVI = massCounties.stream()
                .map(line -> Double.parseDouble(line[0].replace("\"", "")))
                .reduce(new double[2], (result, pvi) -> {
                            var av = (result[1] * result[0] + pvi) / ++result[0];
                            return new double[]{result[0]++, av};
                        },
                        (finalResult, intermediateResult) -> finalResult
                )[1];

        var massSD = 0.0d;
        for (String[] county : massCounties) {
            var a = Double.parseDouble(county[0].replace("\"", ""));
            massSD += Math.pow(a - meanPVI, 2);
        }
        massSD = Math.sqrt(massSD / massCounties.size());
        System.out.println("Mean of massachusetts counties: " + meanPVI);
        System.out.print("SD of massachusetts counties: ");
        System.out.println(massSD);

    }
}

