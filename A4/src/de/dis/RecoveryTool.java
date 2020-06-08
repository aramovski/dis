package de.dis;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasimir on 28.05.2020.
 */
public class RecoveryTool {
    public static void recoverDbFromLog() throws IOException {
        List<Integer> winnerTas = findWinners();
        if(winnerTas == null)
        {
            return;
        }

        List<String> lines = Files.readAllLines(Paths.get("logfile.log"));
        for (String line: lines) {
            if(!line.endsWith(",EOT"))
            {
                int lsn = Integer.parseInt(line.split(",")[0]);
                int tid = Integer.parseInt(line.split(",")[1]);
                int pageid = Integer.parseInt(line.split(",")[2]);
                String data = line.split(",")[3];

                if(winnerTas.contains(tid))
                {
                    boolean fileExists = new File("Page" + pageid + ".data").exists();

                    if(fileExists)
                    {
                        String page = Files.readAllLines(Paths.get("Page" + pageid + ".data")).get(0);
                        int pageLsn = Integer.parseInt(page.split(",")[0]);
                        if(pageLsn <= lsn)
                        {
                            writePage(lsn, pageid, data);
                        }
                    }
                    else
                    {
                        writePage(lsn, pageid, data);
                    }
                }
            }
        }
    }

    private static void writePage(int lsn, int pageid, String data) throws IOException {
        FileWriter fw = new FileWriter("Page" + pageid + ".data", false);
        fw.write(lsn + "," + data);
        fw.close();
    }

    private static List<Integer> findWinners() {
        List<Integer> result = new ArrayList();
        //since per definition, our clients work on seperate pages, we do not have to be careful here.
        //otherwise, this would be an interesting task.
        try {
            List<String> lines = Files.readAllLines(Paths.get("logfile.log"));
            for (String line: lines) {
                if(line.endsWith(",EOT"))
                {
                    result.add(Integer.parseInt(line.split(",")[1]));
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
