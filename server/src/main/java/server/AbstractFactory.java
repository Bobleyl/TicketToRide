package server;

import Shared.IFactory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class AbstractFactory {

    public static IFactory createFactory(String plugin) {

        IFactory factory = null;

        try {
            File pluginDir = new File("plugins");

            for (File file : pluginDir.listFiles()) {

                if (file.isFile() && file.getName().equals(plugin + ".jar")) {

                    URLClassLoader pluginLoader = new URLClassLoader(new URL[] {file.toURI().toURL()}, ClassLoader.getSystemClassLoader());
                    Class klass = Class.forName(plugin + "Factory", true, pluginLoader);
                    factory = (IFactory)klass.newInstance();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return factory;
    }
}
