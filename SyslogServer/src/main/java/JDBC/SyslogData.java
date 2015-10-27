package JDBC;

/**
 * Created by root on 22.10.15.
 */
public abstract class SyslogData {
    /*
    Source Types
     */
    public abstract static class Source_Types{
        public static final Integer hostAgent = 1;
        public static final Integer adminClient = 2;
        public static final Integer arduinoClient = 3;
        public static final Integer accessServer = 4;
    }

    /*
    Event_Types
     */

    public abstract static class Event_Types {
        public abstract static class os_auth{
            public final static Integer login = 1;
            public final static Integer logout = 2;
            public final static Integer timeout = 3;
        }
        public abstract static class room_auth{
            public final static Integer login = 4;
            public final static Integer logout = 5;
        }
        public abstract static class ard_local_err{
            public final static Integer in_work = 6;
            public final static Integer init = 7;
        }
        public final static Integer ard_conn_err = 8;
        public final static Integer ard_unauth = 9;
        public abstract static class adm_cl_auth{
            public final static Integer login = 10;
            public final static Integer logout = 11;
            public final static Integer timeout = 12;
        }
        public abstract static class tech_dep{
            public final static Integer create = 13;
            public final static Integer update = 14;
            public final static Integer delete = 15;
        }
        public abstract static class rec_dep{
            public final static Integer create = 16;
            public final static Integer update = 17;
            public final static Integer delete = 18;
        }
        public abstract static class hr_dep{
            public final static Integer create = 19;
            public final static Integer update = 20;
            public final static Integer delete = 21;
        }
        public abstract static class sec_dep_sys_r{
            public final static Integer create = 22;
            public final static Integer update = 23;
            public final static Integer delete = 24;
        }
        public final static Integer sec_dep_acs_r = 25;
        public abstract static class sec_dep_cards{
            public final static Integer create = 26;
            public final static Integer delete = 27;
        }
        public abstract static class sec_dep_acc{
            public final static Integer create = 28;
            public final static Integer update = 29;
            public final static Integer delete = 30;
        }
    }
}
