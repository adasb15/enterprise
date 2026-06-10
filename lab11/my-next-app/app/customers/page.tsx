import CustomersTable from "./_components/customers-table";
import NewCustomerForm from "./_components/new-customer-form";

export default function CustomersPage() {
  return (
    <>
      <NewCustomerForm />
      <p>&nbsp;</p>
      <CustomersTable />
    </>
  );
}
