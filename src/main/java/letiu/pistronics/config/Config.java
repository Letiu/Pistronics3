package letiu.pistronics.config;

import letiu.pistronics.Pistronics;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config {

    private static final String CATEGORY_GENERAL = "general";

    public static String todo = "TODO!";

    public static void readConfig() {
        Configuration cfg = Pistronics.proxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            Pistronics.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        todo = cfg.getString("todo", CATEGORY_GENERAL, todo, "Write a proper config file");
    }

}
