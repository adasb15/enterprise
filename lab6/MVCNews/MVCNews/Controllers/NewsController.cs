using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using MVCNews.Data;
using MVCNews.Models;

namespace MVCNews.Controllers
{
    public class NewsController : Controller
    {
        private readonly NewsDbContext _context;

        public NewsController(NewsDbContext context)
        {
            _context = context;
        }

        public async Task<IActionResult> Index()
        {
            return View(await _context.News.ToListAsync());
        }

        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var newsItem = await _context.News
                .FirstOrDefaultAsync(m => m.Id == id);
            if (newsItem == null)
            {
                return NotFound();
            }

            return View(newsItem);
        }

        public IActionResult Create()
        {
            var newsItem = new NewsItem
            {
                TimeStamp = DateTime.Now
            };

            return View(newsItem);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,TimeStamp,Text,RowVersion")] NewsItem newsItem)
        {
            if (ModelState.IsValid)
            {
                _context.Add(newsItem);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }

            return View(newsItem);
        }

        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var newsItem = await _context.News.FindAsync(id);
            if (newsItem == null)
            {
                return NotFound();
            }

            return View(newsItem);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,TimeStamp,Text,RowVersion")] NewsItem newsItem)
        {
            if (id != newsItem.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(newsItem);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException e)
                {
                    if (!NewsItemExists(newsItem.Id))
                    {
                        return NotFound();
                    }

                    ModelState.AddModelError("", "Unable to save changes. The record was modified or deleted by another user after you got the original value.");

                    var entry = e.Entries.Single();
                    var databaseEntry = entry.GetDatabaseValues();
                    if (databaseEntry == null)
                    {
                        return NotFound();
                    }

                    var databaseEntity = (NewsItem)databaseEntry.ToObject();

                    newsItem.RowVersion = databaseEntity.RowVersion;
                    ModelState.Remove("RowVersion");

                    ModelState.AddModelError("TimeStamp", "Current value: " + databaseEntity.TimeStamp);
                    ModelState.AddModelError("Text", "Current value: " + databaseEntity.Text);

                    return View(newsItem);
                }

                return RedirectToAction(nameof(Index));
            }

            return View(newsItem);
        }

        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var newsItem = await _context.News
                .FirstOrDefaultAsync(m => m.Id == id);
            if (newsItem == null)
            {
                return NotFound();
            }

            return View(newsItem);
        }

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id, NewsItem newsItem)
        {
            if (id != newsItem.Id)
            {
                return NotFound();
            }

            try
            {
                _context.News.Remove(newsItem);
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException e)
            {
                if (!NewsItemExists(newsItem.Id))
                {
                    return NotFound();
                }

                ModelState.AddModelError("", "Unable to save changes. The record was modified by another user after you got the original value.");

                var entry = e.Entries.Single();
                var databaseEntry = entry.GetDatabaseValues();
                if (databaseEntry == null)
                {
                    return NotFound();
                }

                var databaseEntity = (NewsItem)databaseEntry.ToObject();

                ModelState.Remove("RowVersion");

                return View(databaseEntity);
            }

            return RedirectToAction(nameof(Index));
        }

        private bool NewsItemExists(int id)
        {
            return _context.News.Any(e => e.Id == id);
        }
    }
}
