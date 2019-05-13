package androidteam.cs340.tickettoride.Shared;

import java.lang.reflect.Method;

public class GenericCommand implements Command {
    private String _className;
    private String _methodName;
    private String[] _paramTypes;
    private Object[] _paramValues;

    public GenericCommand(String className, String methodName,
                          String[] paramTypes, Object[] paramValues) {
        _className = className;
        _methodName = methodName;
        _paramTypes = paramTypes;
        _paramValues = paramValues;
    }

    @Override
    public Result execute() {
        try {
            Class<?> receiver = Class.forName(_className);
            Class<?>[] classParamTypes = new Class<?>[_paramTypes.length];
            for(int i = 0; i < _paramTypes.length; ++i) {
                classParamTypes[i] = Class.forName(_paramTypes[i]);
            }
            Method method = receiver.getMethod(_methodName, classParamTypes);
            Object result = method.invoke(null, _paramValues);
            String stringResult = (String)result;
            return new Result(true, stringResult, "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}