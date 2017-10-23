package com.beans;

public enum ScopeType {
        SIGLETON("sigleton"),PROTOTYPE("prototype");
        private String type = "sigleton";
        private ScopeType(String type){
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

}
