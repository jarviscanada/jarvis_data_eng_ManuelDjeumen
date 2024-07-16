//package ca.jrvs.apps.grep;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepImp implements JavaGrep{

    private System LoggerFactory;
    //    private System LoggerFactory;
    //final Logger logger = (Logger)LoggerFactory.getLogger(String.valueOf(JavaGrep.class));

    private String regex;
    private String rootPath;
    private String outFile;

    public JavaGrepImp (String regex, String rootPath, String outFile){
        this.regex = regex;
        this.outFile = outFile;
        this.rootPath = rootPath;
    }


    @Override
    public void process() throws IOException {
        for (File i : listFiles(getRootPath())){
            List<String> lines = readLines(i);
            writeToFile(lines);
        }
    }

    @Override
    public List<File> listFiles(String rootDir) {
        File directory = new File(rootDir);
        if (!directory.isDirectory()){
            System.out.println("This path is not from a Directory");
            return null;
        }
        List<File> output = new LinkedList<>();
        File[] files = directory.listFiles();
        assert files != null;
        Collections.addAll(output, files);
        return output;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> output = new LinkedList<>();
        try {
            FileReader reader = new FileReader(inputFile);
            BufferedReader read = new BufferedReader(reader);
            String line;
            while ((line = read.readLine()) != null){
                output.add(line);
            }
        }

        catch (IllegalArgumentException | FileNotFoundException e){
            System.out.println("The element input is not a File instance");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return output;
    }

    @Override
    public boolean containsPattern(String line) {
        Pattern pattern = Pattern.compile(getRegex());
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        try{
            FileWriter writer = new FileWriter(new File(getOutFile()), true);
            BufferedWriter write = new BufferedWriter(writer);
            for (String i : lines){
                if (containsPattern(i)){
                    write.write(i + "\n");
                }
            }
            write.close();
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String getRootPath() {
        return this.rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return this.regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return this.outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }


    public static void main(String[] args) {
        if (args.length != 3){
            throw new IllegalArgumentException("Usage JavaGrepImp regex roothpath outFile");
        }

        BasicConfigurator.configure();
        JavaGrepImp javaGrepImp = new JavaGrepImp(args[0], args[1], args[2]);

        try {
            javaGrepImp.process();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
//            javaGrepImp.logger.error("Error. Unable to process", ex);
        }
    }
}
