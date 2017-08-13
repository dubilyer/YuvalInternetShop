package dto;

public class AccountDto {
        private long id;
        private String name;

        public AccountDto(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AccountDto that = (AccountDto) o;
            return id == that.id && name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return (int) (id ^ (id >>> 32));
        }

        public AccountDto(long id, String name) {

            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
