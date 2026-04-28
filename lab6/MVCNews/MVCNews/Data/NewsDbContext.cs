using Microsoft.EntityFrameworkCore;
using MVCNews.Models;

namespace MVCNews.Data
{
    public class NewsDbContext : DbContext
    {
        public NewsDbContext(DbContextOptions<NewsDbContext> options)
            : base(options)
        {
        }

        public DbSet<NewsItem> News { get; set; } = null!;
    }
}
